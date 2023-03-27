# SUTABUKS

>스타벅스를 벤치마킹한 음식배달서비스
><br> 프로젝트 개요 -> <a href="https://www.canva.com/design/DAFZamFxwmY/80jjBzyxkYYnk9qE58kZ1g/view?utm_content=DAFZamFxwmY&utm_campaign=designshare&utm_medium=link&utm_source=publishsharelink" target="_blank"><img src="https://img.shields.io/badge/PPT-F46D01?style=flat&logo=PPT&logoColor=white" /></a>


<br>

## 목차
1. [제작 기간 & 제작 인원](#1-제작-기간--제작-인원)
2. [담당 파트](#2-담당-파트)
3. [사용 기술](#3-사용-기술)
4. [게시판 ERD 설계](#4 erd-설계)
5. [주요 Query](#5-주요-query)
6. [주요 Javascript](#6-주요-javascript)
7. [테스트](#7-테스트)

<br><br><br>

## 1. 제작 기간 & 제작 인원
- 2022/12/28 ~ 2023/02/06
- 참여인원 6명(프론트 3명, 백엔드 3명)

<br><br><br>

## 2. 담당 파트
- 회원가입 : 일반회원가입, 점주회원가입, 유효성 체크
- 로그인 
- 아이디&비밀번호 찾기 : 인증번호 생성, 이메일or전화번호로 인증번호 발송, 임시비밀번호 발급
- 마이페이지 : 내정보 조회/수정, 회원탈퇴

<br><br><br>

## 3. 사용 기술
>**Back-end**<br>
<div align=center>
  <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Conda-Forge&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=Gradle&logoColor=white"/>
  <img src="https://img.shields.io/badge/JPA-59666C?style=flat&logo=JPA&logoColor=white"/>
  <img src="https://img.shields.io/badge/Tomcat-F8DC75?style=flat&logo=Apache Tomcat&logoColor=white"/>
</div>

>**open-api**<br>
<div align=center>
 <img src="https://img.shields.io/badge/KakaoMap-FFCD00?style=flat&logo=KakaoMap&logoColor=white"/>
  <img src="https://img.shields.io/badge/ZXING-4285F4?style=flat&logo=ZXING&logoColor=white"/>
  <img src="https://img.shields.io/badge/CoolSMS-9999FF?style=flat&logo=CoolSMS&logoColor=white"/>
</div>

>**형상관리**<br>
<div align=center>
<img src="https://img.shields.io/badge/GitHub-181717?style=for-the-flat&logo=GitHub&logoColor=white">
<img src="https://img.shields.io/badge/Git-F05032?style=for-the-flat&logo=Git&logoColor=white">
</div>

<br><br><br>

## 4. 게시판 ERD 설계
<img src="./src/main/resources/static/img/readme/board_ERD.png">

<br><br><br>

## 6. 주요 Query

<br>

### 6.1 게시글 목록 조회
```
select t1.*
from (
    select
        row_number() over (order by bcdate desc) as num,
        게시글번호, 제목, 작성일, 수정일, 조회수, 별칭, nvl(cnt, 0) as cnt
    from
        게시판테이블
        inner join 회원테이블
        on 게시판테이블.회원아이디 = 회원테이블.회원아이디
        left outer join (
            select 게시글번호, count(*) as cnt
            from 댓글테이블
            group by 게시글번호
        ) t2
        on 게시판테이블.게시글번호 = t2.게시글번호
        (검색조건 추가 위치)
) t1
where t1.num between ?1 and ?2

※?1 : select 결과 중 목록으로 보여줄 row의 첫 행
  ?2 : select 결과 중 목록으로 보여줄 row의 마지막 행
```

<br>

>게시판테이블, 회원테이블, 게시글별 댓글 개수 집계함수 테이블(이하 댓글수테이블)을 join했습니다.
회원아이디가 필수인 게시판테이블과 회원테이블은 inner join, 댓글수테이블은 left outer join으로 join했습니다.
group by와 count(*)로 게시글별 댓글 개수를 집계 후 nvl(cnt, 0)로 댓글이 없어 null인 cnt 값은 0으로 변환하였습니다.
<br><br>
검색조건 추가시 (검색조건 추가 위치)에 검색 조건에 따라 `제목 like '%검색어%'`나 `dbms_lob.instr(게시판테이블.내용, '검색어') > 0`, `별칭 like '%검색어%'`를 추가했습니다.

<br>

### 6.2 게시글 CRUD

- 조회
```
select 게시글번호, 제목, 작성일, 수정일, 내용, 조회수, 회원아이디, 별칭
from 게시판테이블, 회원테이블
where 게시판테이블.회원아이디 = 회원테이블.회원아이디 and 게시글번호 = ?

※? : 조회할 게시글번호 
```

- 등록
```
insert into 게시판테이블 (게시글번호, 카테고리, 제목, 내용, 회원아이디)
values (board_bnum_seq.nextval, 'C0101', ?, ?, ?)

※board_bnum_seq : 게시판번호 시퀀스
  C0101 : 게시판 코드
```

- 수정
```
update 게시판테이블
set 제목 = ? ,
    내용 = ? ,
    수정일 = systimestamp
where 게시글번호 = ? and 회원아이디 = ?
```

- 삭제
```
delete from 게시판테이블
where 게시글번호 = ? and 회원아이디 = ?
```

<br><br><br>

## 7. 주요 Javascript

- 게시글 등록 js 및 작성중 페이지 이동 방지 js
https://github.com/jeon1787/reading_fly/blob/ebc0ea843746bf7d55bb098cca317f10df96864e/src/main/resources/static/js/board/addForm.js#L65-L106

<br>

>이벤트 캡쳐링(Capturing) 단계에서는(`document.body.addEventListener('click', e=>{}, true)`)
뒤로가기 버튼 등 html을 새로 요청하는 경우 `confirm("작성을 종료하시겠습니까?")`창을 띄우고
이벤트 버블링(Bubbling) 단계에서는 게시글 등록 버튼(`addBtn.addEventListener('click', e=>{})`)과
게시판 목록 이동 버튼(`listBtn.addEventListener('click', e=>{})`)이 기능하도록 작성하였습니다.

<br><br><br>

## 8. 단위 테스트

<br>

### 8.1 Postman
<img src="./src/main/resources/static/img/readme/postman_1.png">

<br>

>댓글과 리뷰는 rest API로 제작하고 postman으로 테스트하였습니다.

<br>
<details>
<summary>기타 postman 테스트 보기</summary>

<img width="380" height="200" src="./src/main/resources/static/img/readme/postman_2.png">
<img width="380" height="200" src="./src/main/resources/static/img/readme/postman_3.png">
<img width="380" height="200" src="./src/main/resources/static/img/readme/postman_4.png">
<img width="380" height="200" src="./src/main/resources/static/img/readme/postman_5.png">
<img width="380" height="200" src="./src/main/resources/static/img/readme/postman_6.png">
<img width="380" height="200" src="./src/main/resources/static/img/readme/postman_7.png">

</details>

<br>

### 8.2 JUnit
