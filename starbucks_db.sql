-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- 생성 시간: 23-02-06 10:59
-- 서버 버전: 8.0.31-0ubuntu0.22.04.1
-- PHP 버전: 8.1.2-1ubuntu2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `starbucks_db`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `counpon_member_info`
--

CREATE TABLE `counpon_member_info` (
  `cmi_seq` int NOT NULL,
  `cmi_status` int NOT NULL DEFAULT '1',
  `cmi_use_date` datetime DEFAULT NULL,
  `cmi_ci_seq` int NOT NULL,
  `cmi_mi_seq` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `counpon_member_info`
--

INSERT INTO `counpon_member_info` (`cmi_seq`, `cmi_status`, `cmi_use_date`, `cmi_ci_seq`, `cmi_mi_seq`) VALUES
(1, 1, '2023-02-03 16:25:59', 5, 26),
(2, 1, NULL, 3, 26);

-- --------------------------------------------------------

--
-- 테이블 구조 `coupon_info`
--

CREATE TABLE `coupon_info` (
  `ci_seq` int NOT NULL,
  `ci_discount` int NOT NULL,
  `ci_reg_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ci_ex_dt` datetime NOT NULL,
  `ci_name` varchar(255) NOT NULL,
  `ci_explain` text,
  `ci_stock` int NOT NULL,
  `ci_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `coupon_info`
--

INSERT INTO `coupon_info` (`ci_seq`, `ci_discount`, `ci_reg_dt`, `ci_ex_dt`, `ci_name`, `ci_explain`, `ci_stock`, `ci_code`) VALUES
(3, 1000, '2023-01-20 00:00:00', '2023-01-21 00:00:00', '쿠폰테스트', '설명', 10, 'TESTCODE'),
(5, 1000, '2023-01-20 00:00:00', '2023-01-21 00:00:00', '쿠폰테스트test', '설명test', 101, 'TESTCODEtest'),
(6, 1000, '2023-01-20 00:00:00', '2023-01-21 00:00:00', '쿠폰테스트test', '설명test', 101, 'TESTCODEtest1');

-- --------------------------------------------------------

--
-- 테이블 구조 `event_detail_image`
--

CREATE TABLE `event_detail_image` (
  `edi_seq` int NOT NULL,
  `edi_img_file` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `edi_start_date` date DEFAULT NULL,
  `edi_end_date` date DEFAULT NULL,
  `edi_contents` text,
  `edi_uri` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `event_detail_image`
--

INSERT INTO `event_detail_image` (`edi_seq`, `edi_img_file`, `edi_start_date`, `edi_end_date`, `edi_contents`, `edi_uri`) VALUES
(1, 'Detail_1674633085050.jpg', '2023-01-18', '2023-01-24', NULL, 'new_year_lucky_rabbit_detail'),
(2, 'Detail_1674634005391.jpg', '2023-01-26', '2023-01-30', NULL, 'ice_challenge_cafe_americano_size_up_detail'),
(3, 'Detail_1674634783616.jpg', '2023-01-01', '2023-02-14', NULL, 'new_year_promotion_detail'),
(4, 'Detail_1674634851227.jpg', '2023-01-17', '2023-01-25', NULL, 'new_year_egift_detail'),
(5, 'Detail_1674634921868.jpg', '2023-01-01', '2023-12-31', NULL, 'bonus_star_detail'),
(6, 'Detail_1674635114081.jpg', '2023-01-01', '2023-03-20', NULL, 'barista_favorites_detail'),
(7, 'Detail_1674635197798.jpg', '2023-01-01', '2023-01-31', NULL, 'starbuck_hyundai_january_detail'),
(8, 'Detail_1674635303022.jpg', '2022-10-25', '2099-12-31', '재고 소진 시까지', 'starbuck_kyobogwanghwa_detail');

-- --------------------------------------------------------

--
-- 테이블 구조 `event_image`
--

CREATE TABLE `event_image` (
  `ev_seq` int NOT NULL,
  `ev_img_file` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `ev_uri` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `ev_start_date` date DEFAULT NULL,
  `ev_end_date` date DEFAULT NULL,
  `ev_content` text,
  `ev_title` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `event_image`
--

INSERT INTO `event_image` (`ev_seq`, `ev_img_file`, `ev_uri`, `ev_start_date`, `ev_end_date`, `ev_content`, `ev_title`) VALUES
(1, 'Event_1674633085050.jpg', 'new_year_lucky_rabbit_event', '2023-01-18', '2023-01-24', NULL, '뉴이어 럭키 래빗 이벤트'),
(2, 'Event_1674634005391.jpg', 'ice_challenge_cafe_americano_size_up_event', '2023-01-26', '2023-01-30', NULL, '아이스 챌린지 카페 아메리카노 사이즈 업 이벤트'),
(3, 'Event_1674634783616.jpg', 'new_year_promotion_event', '2023-01-01', '2023-02-14', NULL, '뉴이어 프로모션'),
(4, 'Event_1674634851227.jpg', 'new_year_egift_event', '2023-01-17', '2023-01-25', NULL, '설 e-Gift 선물하기 이벤트'),
(5, 'Event_1674634921868.jpg', 'bonus_star_event', '2023-01-01', '2023-12-31', NULL, '만원 별 적립 이벤트'),
(6, 'Event_1674635114081.jpg', 'barista_favorites_event', '2023-01-01', '2023-03-20', NULL, 'BARISTA FAVORITES'),
(7, 'Event_1674635197798.jpg', 'starbuck_hyundai_january_event', '2023-01-01', '2023-01-31', NULL, '스타벅스 현대카드 1월 한정 혜택'),
(8, 'Event_1674635303022.jpg', 'starbuck_kyobogwanghwa_event', '2022-10-25', '2099-12-31', '재고 소진 시까지', '스타벅스 광화문교보문고점 단편선 이벤트');

-- --------------------------------------------------------

--
-- 테이블 구조 `membership_card`
--

CREATE TABLE `membership_card` (
  `card_seq` int NOT NULL,
  `card_name` varchar(64) NOT NULL DEFAULT '수타벅스멤버십카드',
  `card_money` int DEFAULT '0',
  `card_mi_seq` int NOT NULL,
  `card_image` int DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `membership_card`
--

INSERT INTO `membership_card` (`card_seq`, `card_name`, `card_money`, `card_mi_seq`, `card_image`) VALUES
(10, '수타벅스 카드', 10000, 88, 1),
(11, '수타벅스 카드', 0, 87, 1),
(12, '수타벅스 카드', 0, 65, 1),
(13, '수타벅스 카드', 0, 84, 1),
(14, '수타벅스 카드', 0, 74, 1),
(26, '수타벅스 카드', 74600, 26, 1);

-- --------------------------------------------------------

--
-- 테이블 구조 `membership_card_image`
--

CREATE TABLE `membership_card_image` (
  `cardimage_seq` int NOT NULL,
  `cardimage_name` varchar(64) NOT NULL,
  `cardimage_file` text NOT NULL,
  `cardimage_uri` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `membership_card_image`
--

INSERT INTO `membership_card_image` (`cardimage_seq`, `cardimage_name`, `cardimage_file`, `cardimage_uri`) VALUES
(1, 'image1', 'Membership_1674713313410.jpg', 'membership1');

-- --------------------------------------------------------

--
-- 테이블 구조 `membership_card_qr_image`
--

CREATE TABLE `membership_card_qr_image` (
  `cardqr_seq` int NOT NULL,
  `cardqr_file` text NOT NULL,
  `cardqr_uri` text NOT NULL,
  `cardqr_mi_seq` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `membership_card_qr_image`
--

INSERT INTO `membership_card_qr_image` (`cardqr_seq`, `cardqr_file`, `cardqr_uri`, `cardqr_mi_seq`) VALUES
(10, 'MembershipCard_MemberNo_88.jpg', 'MembershipCard_MemberNo_88', 88),
(11, 'MembershipCard_MemberNo_87.jpg', 'MembershipCard_MemberNo_87', 87),
(12, 'MembershipCard_MemberNo_65.jpg', 'MembershipCard_MemberNo_65', 65),
(13, 'MembershipCard_MemberNo_84.jpg', 'MembershipCard_MemberNo_84', 84),
(14, 'MembershipCard_MemberNo_74.jpg', 'MembershipCard_MemberNo_74', 74);

-- --------------------------------------------------------

--
-- 테이블 구조 `member_grade_info`
--

CREATE TABLE `member_grade_info` (
  `mgi_seq` int NOT NULL,
  `mgi_total_order` int NOT NULL,
  `mgi_grade_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mgi_mi_seq` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `member_grade_info`
--

INSERT INTO `member_grade_info` (`mgi_seq`, `mgi_total_order`, `mgi_grade_name`, `mgi_mi_seq`) VALUES
(1, 0, '브론즈', 1),
(2, 0, '실버', 2),
(3, 0, '골드', 3);

-- --------------------------------------------------------

--
-- Stand-in structure for view `member_grade_view`
-- (See below for the actual view)
--
CREATE TABLE `member_grade_view` (
`cnt` bigint
,`member_grade` varchar(3)
,`sb_mi_seq` int
);

-- --------------------------------------------------------

--
-- 테이블 구조 `member_info`
--

CREATE TABLE `member_info` (
  `mi_seq` int NOT NULL,
  `mi_id` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mi_pwd` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mi_name` varchar(55) NOT NULL,
  `mi_nickname` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mi_birth` date NOT NULL,
  `mi_gen` int DEFAULT '0',
  `mi_phonenum` varchar(20) NOT NULL,
  `mi_status` int DEFAULT '1',
  `mi_reg_date` date NOT NULL DEFAULT (curdate()),
  `mi_group` int NOT NULL DEFAULT '1',
  `mi_business_num` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `mi_address` varchar(250) NOT NULL,
  `mi_last_login` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mi_detail_address` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `mi_sbi_seq` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `member_info`
--

INSERT INTO `member_info` (`mi_seq`, `mi_id`, `mi_pwd`, `mi_name`, `mi_nickname`, `mi_birth`, `mi_gen`, `mi_phonenum`, `mi_status`, `mi_reg_date`, `mi_group`, `mi_business_num`, `mi_address`, `mi_last_login`, `mi_detail_address`, `mi_sbi_seq`) VALUES
(2, 'user002', '@a123456', '김승지', '김승지(닉네임)', '1995-03-27', 2, '010-2222-2222', 1, '2023-01-02', 1, '000-11-11111', '동구-칠산동', '2023-01-02 00:00:00', '침산로22길', NULL),
(3, 'user003', '@a123456', '박승지', '박승지(닉네임)', '1995-04-27', 1, '010-3333-3333', 2, '2023-01-02', 1, '000-00-00000', '서구-산격동', '2023-01-02 00:00:00', '침산로23길', NULL),
(10, 'user004', 'ftxyWiX8xZVrBX8HYrsJ6w==', '김길동', '김길동(닉)', '2023-01-03', 1, '010-5555-5155', 3, '2023-01-04', 1, '000-00-00020', '북구-침산동', '2023-01-04 17:34:10', '푸르지오1차', NULL),
(15, 'user011', 'DIn3D7rcdne88EM+V2TdhQ==', 'testName011', 'testNickname011', '2023-01-12', 0, '010-0000-0011', 1, '2023-01-12', 1, NULL, '00구-00동', '2023-01-12 14:42:40', '00아파트', NULL),
(16, 'user012', 'DIn3D7rcdne88EM+V2TdhQ==', 'testName012', 'testNickname011', '2023-01-12', 0, '010-0000-0012', 1, '2023-01-12', 1, NULL, '00구-00동', '2023-01-12 15:32:00', '00아파트', NULL),
(17, 'asd123@naver.com', 'DIn3D7rcdne88EM+V2TdhQ==', 'testName012', 'testNickname011', '2023-01-12', 0, '010-0000-0014', 1, '2023-01-12', 1, NULL, '00구-00동', '2023-01-12 16:37:50', '00아파트', NULL),
(19, 'testid@gmail.com', 'KmwjxNXXiRgxNkIFHccDLw==', 'xx로01길-25', '김길동(닉네임)', '2023-01-12', 1, '010-1111-2222', 1, '2023-01-12', 1, NULL, '00구-00동', '2023-01-12 00:00:00', '00로21-23', NULL),
(23, 'owner1', '123456789', '홍길동', '홍동(닉네임)', '2023-01-12', 1, '010-1111-2232', 1, '2023-01-12', 1, 'ㅁㄴㅇ', '00구-00동', '2023-01-12 17:09:07', '00로21-23', NULL),
(26, 'asdf', 'Pxu/fkAEsEJSICzKluN92Q==', '홍길동a', '홍길동a', '2023-01-12', 1, '010-1113-2222', 1, '2023-01-12', 1, NULL, '00구-00동', '2023-01-12 17:10:12', '00로21-23', NULL),
(42, 'dhf@gmail.com', 'NYp1HkRvYu7i+aqZF3ezMg==', '4', '2', '2023-01-12', 1, '010-1234-9576', 3, '2023-01-20', 1, NULL, '서울특별시 용산구', '2023-01-20 00:00:00', '한남동 한남더힐', NULL),
(43, 'tes@gmail.com', 'noKIe730fEIl9fu9hxgopg==', '홍길동', '홍길동(닉네임)', '2023-01-12', 1, '010-0001-0001', 1, '2023-01-20', 1, NULL, '00구-00동', '2023-01-20 17:39:40', '00로21-23', NULL),
(44, 'tesa@gmail.com', 'noKIe730fEIl9fu9hxgopg==', '홍길동', '홍길동(닉네임)', '2023-01-12', 1, '010-0001-5001', 1, '2023-01-20', 1, NULL, '00구-00동', '2023-01-20 17:42:18', '00로21-23', NULL),
(45, 'tesaa@gmail.com', 'z2I4S9FQOwwJWndmJzcgjA==', '홍길동', '홍길동(닉네임)', '2023-01-12', 1, '010-0001-6001', 1, '2023-01-20', 1, NULL, '00구-00동', '2023-01-20 17:42:33', '00로21-23', NULL),
(46, 'tesaaa@gmail.com', 'XWnS5Su05KW56GBFO6VoHQ==', '홍길동', '홍길동(닉네임)', '2023-01-12', 1, '010-0041-6001', 1, '2023-01-20', 1, NULL, '00구-00동', '2023-01-20 17:42:44', '00로21-23', NULL),
(47, 'teaa@gmail.com', 'XWnS5Su05KW56GBFO6VoHQ==', '홍길동', '홍길동(닉네임)', '2023-01-12', 1, '010-0541-6001', 1, '2023-01-25', 1, NULL, '한남더힐', '2023-01-25 16:30:09', NULL, NULL),
(48, 'teaa55@gmail.com', 'XWnS5Su05KW56GBFO6VoHQ==', '홍길동', '홍길동(닉네임)', '2023-01-12', 1, '010-1541-6001', 1, '2023-01-25', 1, NULL, '00', '2023-01-25 16:30:22', NULL, NULL),
(49, 'taa55@gmail.com', 'XWnS5Su05KW56GBFO6VoHQ==', '홍길동', '홍길동(닉네임)', '2023-01-12', 1, '010-2541-6001', 1, '2023-01-25', 1, NULL, '00', '2023-01-25 17:02:45', NULL, NULL),
(59, 'storeId@store.com', 'hp9LBGoLT9P/H38gIpD63w==', '4', '2', '2023-01-12', 1, '010-1234-9576', 1, '2023-02-01', 2, '123-45-67890', '서울특별시 용산구', '2023-02-01 00:00:00', '한남동 한남더힐', NULL),
(63, 'asd14423@naver.com', 'pxeAT/xhdlVlmzjqCmckuQ==', '이름', 'bin1111', '2023-02-03', 0, '010-000-222', 1, '2023-02-01', 1, NULL, '서울 서초구 나루터로 15 (잠원동, 신동초등학교)', '2023-02-01 10:39:26', '101호', NULL),
(64, 'test1@gmail.com', 'jnKhw154EVGykZulcm11JA==', 'testName1', 'testNickName', '2023-02-01', 1, '010-0000-0001', 1, '2023-02-01', 1, NULL, 'testAdress1', '2023-02-01 12:19:58', 'testDetailAdress1', NULL),
(65, 'fff@fff.net', 'pxeAT/xhdlVlmzjqCmckuQ==', '이도영', '올해롯데우승함', '2023-02-23', 0, '01052525252', 1, '2023-02-01', 2, '205-52-25502', '서울 중구 을지로 149 (산림동, 남일금속)', '2023-02-01 12:45:20', '', 3),
(66, 'ggg@ggg.net', 'pxeAT/xhdlVlmzjqCmckuQ==', '이예이', '꿀잼6', '2023-02-06', 0, '01068262130', 1, '2023-02-01', 2, '111111111124', '서울 강남구 가로수길 5 (신사동)', '2023-02-01 12:46:19', '', NULL),
(67, 'testid@gmil.com', 'DIn3D7rcdne88EM+V2TdhQ==', '홍길동', '홍동(닉네임)', '2023-01-12', 1, '010-1111-2732', 1, '2023-02-01', 1, NULL, '00구-00동', '2023-02-01 13:31:52', '00로21-23', NULL),
(68, 'nomal@gmil.com', 'DIn3D7rcdne88EM+V2TdhQ==', '일반회원', '일반회원닉네임', '2023-01-12', 1, '010-1234-1111', 1, '2023-02-01', 1, NULL, '00구-00동', '2023-02-01 15:35:05', '00로21-23', NULL),
(70, 'aaa@aaa.com', 'wTkXCjoSg5YFVujZGSwMCg==', '테스트', '테스트', '2023-02-01', 0, '01011111111', 1, '2023-02-02', 1, NULL, '대구 달서구 호산로 125 (파호동, 성서삼성명가타운)', '2023-02-02 11:11:05', '', NULL),
(74, 'b1@bbb.net', 'QQzL7cONFkCGjfsINixQFA==', '손현지', '구운계란', '2023-02-15', 0, '01088479819', 3, '2023-02-02', 1, NULL, '서울 강남구 가로수길 5 (신사동)', '2023-02-02 00:00:00', '201', NULL),
(77, 'tlzmlt2456@gmail.com', 'hp9LBGoLT9P/H38gIpD63w==', '11111승지수정', '닉네임이안바뀐다고?', '2023-01-12', 1, '010-3676-9899', 3, '2023-02-02', 1, NULL, '서울특별시 용산구', '2023-02-02 00:00:00', '한남동 한남더힐', NULL),
(78, 'tlzmlt245678@gmail.com', 'DIn3D7rcdne88EM+V2TdhQ==', '점주류승지', '점주류승지(닉네임)', '2023-01-12', 1, '545484', 1, '2023-02-02', 2, '122-45-78910', '00구-00동', '2023-02-02 16:16:52', '00로21-23', NULL),
(79, 'tlzmlt2456@gmail22.com', 'cuPJ6hdDlzxBaAtEn7IlXQ==', '류승지12', '류승지12(닉네임)', '2023-01-12', 1, '01036769588', 1, '2023-02-02', 1, NULL, '00구-00동', '2023-02-02 00:00:00', '00로21-23', NULL),
(80, 'a2@aaa.net', 'jeYAX3g4yaOY4wfoiCpkmA==', '옥지은', '꿀잼lover', '2023-02-07', 0, '01085491439', 1, '2023-02-02', 1, NULL, '서울 강남구 가로수길 5 (신사동)', '2023-02-02 00:00:00', '202호', NULL),
(82, 'dhftlcm@gmail.com', 'wOkl2Qng2vsCJy6kFHnJvA==', '류승지', '류승지(닉네임)', '2023-01-12', 1, '01036769550', 1, '2023-02-02', 1, NULL, '대구시 북구', '2023-02-02 00:00:00', '00로 00길 000동 000호', NULL),
(83, 'dhftlcm12@gmail.com', '4Qx3D+JNOOvhR6Z1UdAIUg==', '수정테스트', '류승지12(닉네임)', '2023-01-12', 1, '010-3676-9550', 1, '2023-02-03', 1, NULL, '서울특별시 용바다구', '2023-02-03 00:00:00', '둘남동 둘남더힐', NULL),
(84, 's1@sss.net', 'pxeAT/xhdlVlmzjqCmckuQ==', '오한수', '떳다떳다비행기', '2023-02-14', 0, '01068657181', 1, '2023-02-03', 1, NULL, '서울 강남구 가로수길 12 (신사동)', '2023-02-03 00:00:00', '1호', NULL),
(85, 'm1@mmm.net', 'pxeAT/xhdlVlmzjqCmckuQ==', '김철호', '해파리', '2023-02-21', 0, '01053393459', 1, '2023-02-03', 2, '987-65-43210', '서울 강남구 가로수길 5 (신사동)', '2023-02-03 09:56:48', '1호점', NULL),
(86, 'yen.greact@gmail.com', 'pxeAT/xhdlVlmzjqCmckuQ==', '이예은', '꿀잼', '2023-02-08', 0, '01068262131', 1, '2023-02-03', 1, NULL, '서울 동대문구 서울시립대로 5 (답십리동, 신답극동아파트)', '2023-02-03 00:00:00', '2호', NULL),
(87, 's2@sss.net', 'pxeAT/xhdlVlmzjqCmckuQ==', '김철호', '그린컴퓨터', '2023-02-01', 0, '01022222222', 3, '2023-02-03', 1, NULL, '서울 강남구 가로수길 5 (신사동)', '2023-02-03 00:00:00', '201호', NULL),
(88, 'a1@a.com', 'wTkXCjoSg5YFVujZGSwMCg==', '테스트용', '테스트용', '2023-02-01', 0, '01012345678', 1, '2023-02-03', 1, NULL, '대구 남구 월배로 지하 501 (대명동, 서부정류장역)', '2023-02-03 20:27:21', '', NULL),
(89, 'mintmint02@mint.com', 'pxeAT/xhdlVlmzjqCmckuQ==', '김이박', '닉네임3', '2023-02-16', 0, '01012341234', 1, '2023-02-05', 1, NULL, '대구 중구 달구벌대로 지하 2100 (덕산동, 반월당역)', '2023-02-05 03:04:11', '반월당역', NULL),
(90, 'tlzmlt241@gmail.com', '+iaSAPwsw1nPoIB32i7Lgw==', '류승지12', '류승지12(닉네임)', '2023-01-12', 1, '010-002202-1254', 1, '2023-02-05', 1, NULL, '00구-00동', '2023-02-05 14:33:07', '00로21-23', NULL),
(91, 'parkmawani@github.com', '6zAK2TGgUJdSs/AVD3E/2w==', '박진혁', '박진혁', '2001-08-30', 0, '1111111111', 1, '2023-02-05', 1, NULL, '대구 중구 동성로 1 (동성로3가)', '2023-02-05 22:26:58', 'ㅅㄷㄴㅅ', NULL),
(92, 'a22@aaa.com', 'sx7/c9hRlEXMYWUHdd9PYw==', '이예은123', 'aaa1234', '2023-02-03', 0, '01022221224', 1, '2023-02-06', 1, NULL, '경기 성남시 분당구 대왕판교로 477 (판교동, 낙생고등학교)', '2023-02-06 10:35:23', '102호', NULL);

-- --------------------------------------------------------

--
-- 테이블 구조 `menu_basic_info`
--

CREATE TABLE `menu_basic_info` (
  `mbi_seq` int NOT NULL,
  `mbi_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mbi_cost` int NOT NULL,
  `mbi_status` int NOT NULL DEFAULT '1',
  `mbi_explain` text NOT NULL,
  `mbi_pc_seq` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `menu_basic_info`
--

INSERT INTO `menu_basic_info` (`mbi_seq`, `mbi_name`, `mbi_cost`, `mbi_status`, `mbi_explain`, `mbi_pc_seq`) VALUES
(1, '아메리카노', 4500, 1, '진한 에스프레소와 뜨거운 물을 섞어 스타벅스의 깔끔하고 강렬한 에스프레소를 가장 부드럽게 잘 느낄 수 있는 커피', 3),
(2, '아이스 아메리카노', 4500, 1, '진한 에스프레소에 시원한 정수물과 얼음을 더하여 스타벅스의 깔끔하고 강렬한 에스프레소를 가장 부드럽고 시원하게 즐길 수 있는 커피', 3),
(3, '에스프레소', 4000, 1, '스타벅스 에스프레소는 향기로운 크레마 층과 바디 층, 하트 층으로 이루어져 있으며, 입안 가득히 커피와 달콤한 카라멜 향이 느껴지는 커피 음료', 3),
(4, '카페 라떼', 5000, 1, '풍부하고 진한 에스프레소가 신선한 스팀 밀크를 만나 부드러워진 커피 위에 우유 거품을 살짝 얹은 대표적인 커피 라떼', 3),
(5, '아이스 카페 라떼', 5000, 1, '풍부하고 진한 농도의 에스프레소가 시원한 우유와 얼음을 만나 고소함과 시원함을 즐길 수 있는 대표적인 커피 라떼', 3),
(6, '카페 모카', 5500, 1, '진한 초콜릿 모카 시럽과 풍부한 에스프레소를 스팀 밀크와 섞어 휘핑크림으로 마무리한 음료로 진한 에스프레소와 초콜릿 맛이 어우러진 커피', 3),
(7, '아이스 카페 모카', 5500, 1, '진한 초콜릿 모카 시럽과 풍부한 에스프레소를 신선한 우유 그리고 얼음과 섞어 휘핑크림으로 마무리한 음료로 진한 에스프레소와 초콜릿 맛이 어우러진 커피', 3),
(8, '돌체 라떼', 5900, 1, '스타벅스의 다른 커피 음료보다 더욱 깊은 커피의 맛과 향에 깔끔한 무지방 우유와 부드러운 돌체 시럽이 들어간 음료로 달콤하고 진한 커피 라떼', 3),
(9, '아이스 돌체 라떼', 5900, 1, '스타벅스의 다른 커피 음료보다 더욱 깊은 커피의 맛과 향에 깔끔한 무지방 우유와 부드러운 돌체 시럽이 들어간 음료로 달콤하고 진한 커피 라떼', 3),
(10, '카라멜 마키아또', 5900, 1, '향긋한 바닐라 시럽과 따뜻한 스팀 밀크 위에 풍성한 우유 거품을 얹고 점을 찍듯이 에스프레소를 부은 후 벌집 모양으로 카라멜 드리즐을 올린 달콤한 커피 음료', 3),
(11, '아이스 카라멜 마키아또', 5900, 1, '향긋한 바닐라 시럽과 시원한 우유와 얼음을 넣고 점을 찍듯이 에스프레소를 부은 후 벌집 모양으로 카라멜 드리즐을 올린 달콤한 커피 음료', 3),
(12, '에스프레소 프라푸치노', 5500, 1, '에스프레소 샷의 강렬함과 약간의 단맛이 어우러진 프라푸치노', 4),
(13, '더블 에스프레소 칩 프라푸치노', 6300, 1, '리스트레토 에스프레소 2샷과 에스프레소 칩, 하프앤하프가 진하게 어우러진 커피의 기본에 충실한 프라푸치노', 4),
(14, '자바 칩 프라푸치노', 6300, 1, '커피, 모카 소스, 진한 초콜릿 칩이 입안 가득 느껴지는 스타벅스에서만 맛볼 수 있는 프라푸치노', 4),
(15, '모카 프라푸치노', 5900, 1, '초콜릿과 커피가 어우러진 프라푸치노', 4),
(16, '화이트 초콜릿 모카 프라푸치노', 6000, 1, '화이트 초콜릿, 커피와 우유가 조화로운 프라푸치노', 4),
(17, '바닐라 크림 프라푸치노', 5100, 1, '신선한 우유와 바닐라 시럽이 어우러진 크림 프라푸치노', 4),
(18, '제주 유기농 말차로 만든 크림 프라푸치노', 6300, 1, '깊고 진한 말차 본연의 맛과 향을 시원하고 부드럽게 즐길 수 있는 프라푸치노', 4),
(19, '까망 크림 프라푸치노', 7500, 1, '까망 라떼의 프라푸치노 버전으로 쫄깃한 국내산 흑임자 떡과 블랙 소보로 토핑으로 컵빙수처럼 먹는 음료. 고소한 국내산 흑임자와 쫄깃한 국내산 흑임자 떡, 달콤한 블랙 소보로 토핑으로 제주의 돌 하르방 길을 느낄 수 있는 음료', 4),
(20, '레드벨벳 크림치즈 케이크', 5500, 1, '레드벨벳 시트 사이에 크림치즈 무스를 샌드한 케이크입니다.', 5),
(21, '7 레이어 가나슈 케이크', 5700, 1, '초콜릿, 가나슈, 모카로 만든 시트와 크림이 7개의 층을 이루어 모양부터 매력적인 케이크입니다.', 5),
(22, '부드러운 고구마 생크림 케이크', 5900, 1, '부드러운 고구마 무스와 조각 형태의 고구마를 화이트 케이크 시트 사이에 듬뿍 넣고 생크림을 올린 달콤하고 고소한 케이크입니다', 5),
(23, '클라우드 치즈 케이크', 5500, 1, '사워크림의 상큼함과 진한 치즈의 맛을 동시에 맛볼 수 있는 케이크입니다.', 5),
(24, '마스카포네 티라미수 케이크', 5900, 1, '고소한 마스카포네 치즈 크림에 촉촉한 커피 시트가 입안을 감싸는 기분 좋은 느낌의 떠먹는 티라미수입니다.', 5),
(25, '딸기 담은 마스카포네 케이크', 7900, 1, '신선한 생딸기와 진한 마스카포네 치즈 크림의 조화가 어우러지는 새콤 달콤한 떠먹는 케이크입니다.', 5),
(26, '블루베리 쿠키 치즈 케이크', 6900, 1, '달콤한 블루베리를 듬뿍 올린 진한 풍미의 쿠키 치즈 케이크입니다.', 5),
(27, '슈크림 가득 바움쿠헨', 6900, 1, '바닐라 빈이 들어간 부드러운 슈크림과 바움쿠헨이 조화로운 바닐라 풍미의 케이크입니다.', 5),
(28, 'V.L.T. 샌드위치', 7700, 1, '비건 오믈렛(식물성 대체 오믈렛), 로메인 상추, 토마토를 넣어 만든 샌드위치로 지구를 위한 이유 있는 편식, Plant Based Food입니다.', 6),
(29, 'B.E.L.T. 샌드위치', 5900, 1, '주 재료인 베이컨(Bacon), 계란(Egg), 로메인 상추(Lettuce-Romane), 토마토(Tomato)의 각각의 머리 글자를 따온 이름의 샌드위치 입니다.', 6),
(30, '단호박 에그 샌드위치', 4900, 1, '건강 잡곡 식빵 속에 단호박, 달걀, 토마토, 치즈 등을 넣은 콜드 샌드위치입니다.\n*단호박 & 에그 스프레드와 볶음 양송이, 토마토, 스위스 치즈, 로메인이 들어있습니다.', 6),
(31, '햄&루꼴라 올리브 샌드위치', 6200, 1, '햄, 토마토, 모짜렐라 치즈와 루꼴라를 올리브가 콕콕 박힌 치아바타 사이에 넣은 샌드위치입니다.', 6),
(32, '루벤 샌드위치', 6500, 1, '곡물 사워도우 브레드 사이에 파스트라미와 적양배추로 만든 사우어 크라우트가 들어간 샌드위치입니다.', 6),
(33, '바비큐 치킨 치즈 치아바타', 5800, 1, '바삭한 치아바타에 새콤달콤한 바비큐 소스 치킨, 베이컨, 치즈가 어우러진 샌드위치입니다.', 6),
(34, '에그 샌드위치', 4400, 1, '화이트 식빵 사이에 고소한 에그 스프레드를 넣은 부드러운 샌드위치입니다.', 6),
(35, '잠봉 베이글 샌드위치', 6300, 1, '고소한 세사미 베이글에 진한 풍미의 치즈버터를 바르고 베이글 사이에 잠봉 햄, 치즈, 루꼴라를 더한 샌드위치입니다.', 6);

-- --------------------------------------------------------

--
-- Stand-in structure for view `menu_basket_info`
-- (See below for the actual view)
--
CREATE TABLE `menu_basket_info` (
`mbi_cost` int
,`mbi_explain` text
,`mbi_name` varchar(64)
,`mbi_pc_seq` int
,`mbi_seq` int
,`mbi_status` int
,`mii_img_file` text
,`mii_number` int
,`mii_seq` int
,`mii_uri` text
);

-- --------------------------------------------------------

--
-- 테이블 구조 `menu_image_info`
--

CREATE TABLE `menu_image_info` (
  `mii_seq` int NOT NULL,
  `mii_img_file` text NOT NULL,
  `mii_number` int NOT NULL,
  `mii_uri` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `menu_image_info`
--

INSERT INTO `menu_image_info` (`mii_seq`, `mii_img_file`, `mii_number`, `mii_uri`) VALUES
(1, 'Menu_1675136060494.jpg', 1, 'hot_americano'),
(2, 'Menu_1675136761747.jpg', 2, 'ice_americano'),
(3, 'Menu_1675136812899.jpg', 3, 'espresso'),
(4, 'Menu_1675136863636.jpg', 4, 'hot_caffe_latte'),
(5, 'Menu_1675136897390.jpg', 5, 'ice_caffe_latte'),
(6, 'Menu_1675136990432.jpg', 6, 'hot_caffe_mocha'),
(7, 'Menu_1675137056891.jpg', 7, 'ice_caffe_mocha'),
(8, 'Menu_1675137134340.jpg', 8, 'hot_dolce_latte'),
(9, 'Menu_1675137166018.jpg', 9, 'ice_dolce_latte'),
(10, 'Menu_1675137221372.jpg', 10, 'hot_caramel_macchiato'),
(11, 'Menu_1675137313104.jpg', 11, 'ice_caramel_macchiato'),
(12, 'Menu_1675137567974.jpg', 12, 'espresso_frappuccino'),
(13, 'Menu_1675137645575.jpg', 13, 'double_espresso_chip_frappuccino'),
(14, 'Menu_1675137712645.jpg', 14, 'java_chip_frappuccino'),
(15, 'Menu_1675137794140.jpg', 15, 'mocha_frappuccino'),
(16, 'Menu_1675137838760.jpg', 16, 'white_chocolate_mocha_frappuccino'),
(17, 'Menu_1675137924164.jpg', 17, 'vanilla_cream_frappuccino'),
(18, 'Menu_1675137989002.jpg', 18, 'malcha_cream_frappuccino'),
(19, 'Menu_1675138076496.jpg', 19, 'black_cream_frappuccino'),
(20, 'Menu_1675138211604.jpg', 20, 'redvelvet_cream_cheese_cake'),
(21, 'Menu_1675138279642.jpg', 21, 'seven_layer_ganache_cake'),
(22, 'Menu_1675138356203.jpg', 22, 'sweetpotato_cream_cake'),
(23, 'Menu_1675138407714.jpg', 23, 'cloud_cheese_cake'),
(24, 'Menu_1675138470470.jpg', 24, 'mascarpone_tiramisu_cake'),
(25, 'Menu_1675138510580.jpg', 25, 'strawberry_mascarpone_cake'),
(26, 'Menu_1675138571370.jpg', 26, 'blueberry_cookie_cheese_cake'),
(27, 'Menu_1675138702219.jpg', 27, 'choux_cream_baumkuchen'),
(28, 'Menu_1675142602386.jpg', 28, 'vlt_sandwich'),
(29, 'Menu_1675142650889.jpg', 29, 'belt_sandwich'),
(30, 'Menu_1675142698590.jpg', 30, 'sweet_pumpkin_egg_sandwich'),
(31, 'Menu_1675142755160.jpg', 31, 'ham_rucola_olive_sandwich'),
(32, 'Menu_1675142804259.jpg', 32, 'reuben_sandwich'),
(33, 'Menu_1675142878665.jpg', 33, 'barbeque_chicken_cheese_ciabatta'),
(34, 'Menu_1675142968384.jpg', 34, 'egg_sandwich'),
(35, 'Menu_1675143004237.jpg', 35, 'jambon_bagel_sandwich');

-- --------------------------------------------------------

--
-- 테이블 구조 `menu_nutrition`
--

CREATE TABLE `menu_nutrition` (
  `mn_seq` int NOT NULL,
  `mn_img_file` text NOT NULL,
  `mn_uri` text NOT NULL,
  `mn_mbi_seq` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `menu_nutrition`
--

INSERT INTO `menu_nutrition` (`mn_seq`, `mn_img_file`, `mn_uri`, `mn_mbi_seq`) VALUES
(1, 'Menu_1675144575958.jpg', 'americano_nutrition', 1),
(2, 'Menu_1675144621304.jpg', 'ice_americano_nutrition', 2),
(3, 'Menu_1675144805578.jpg', 'espresso_nutrition', 3),
(4, 'Menu_1675144868130.jpg', 'caffelatte_nutrition', 4),
(5, 'Menu_1675146507285.jpg', 'ice_caffelatte_nutrition', 5),
(6, 'Menu_1675146791960.jpg', 'cafemocha_nutrition', 6),
(7, 'Menu_1675147420914.jpg', 'ice_cafemocha_nutrition', 7),
(8, 'Menu_1675150608140.jpg', 'hot_dolce_latte', 8),
(9, 'Menu_1675150697204.jpg', 'ice_dolce_latte', 9),
(10, 'Menu_1675150719179.jpg', 'hot_caramel_macchiato', 10),
(11, 'Menu_1675150769618.jpg', 'ice_caramel_macchiato', 11),
(12, 'Menu_1675150786674.jpg', 'espresso_frappuccino', 12),
(13, 'Menu_1675150801466.jpg', 'double_espresso_chip_frappuccino', 13),
(14, 'Menu_1675150824255.jpg', 'java_chip_frappuccino', 14),
(15, 'Menu_1675150848323.jpg', 'mocha_frappuccino', 15),
(16, 'Menu_1675150863871.jpg', 'white_chocolate_mocha_frappuccino', 16),
(17, 'Menu_1675150923601.jpg', 'vanilla_cream_frappuccino', 17),
(18, 'Menu_1675150934446.jpg', 'malcha_cream_frappuccino', 18),
(19, 'Menu_1675150951951.jpg', 'black_cream_frappuccino', 19),
(20, 'Menu_1675150966083.jpg', 'redvelvet_cream_cheese_cake', 20),
(21, 'Menu_1675150978668.jpg', 'seven_layer_ganache_cake', 21),
(22, 'Menu_1675150990982.jpg', 'sweetpotato_cream_cake', 22),
(23, 'Menu_1675150999375.jpg', 'cloud_cheese_cake', 23),
(24, 'Menu_1675151011142.jpg', 'mascarpone_tiramisu_cake', 24),
(25, 'Menu_1675151024435.jpg', 'strawberry_mascarpone_cake', 25),
(26, 'Menu_1675151035128.jpg', 'blueberry_cookie_cheese_cake', 26),
(27, 'Menu_1675151046042.jpg', 'choux_cream_baumkuchen', 27),
(28, 'Menu_1675151057170.jpg', 'vlt_sandwich', 28),
(29, 'Menu_1675151070012.jpg', 'belt_sandwich', 29),
(30, 'Menu_1675151080859.jpg', 'sweet_pumpkin_egg_sandwich', 30),
(31, 'Menu_1675151093710.jpg', 'ham_rucola_olive_sandwich', 31),
(32, 'Menu_1675151110531.jpg', 'reuben_sandwich', 32),
(33, 'Menu_1675151121708.jpg', 'barbeque_chicken_cheese_ciabatta', 33),
(34, 'Menu_1675151131215.jpg', 'egg_sandwich', 34),
(35, 'Menu_1675151141097.jpg', 'jambon_bagel_sandwich', 35);

-- --------------------------------------------------------

--
-- 테이블 구조 `menu_option_category`
--

CREATE TABLE `menu_option_category` (
  `moc_seq` int NOT NULL,
  `moc_name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `menu_option_category`
--

INSERT INTO `menu_option_category` (`moc_seq`, `moc_name`) VALUES
(1, '사이즈'),
(2, '커피'),
(3, '시럽'),
(4, '얼음'),
(5, '휘핑크림'),
(6, '자바칩'),
(7, '드리즐');

-- --------------------------------------------------------

--
-- 테이블 구조 `menu_option_info`
--

CREATE TABLE `menu_option_info` (
  `moi_seq` int NOT NULL,
  `moi_name` varchar(64) NOT NULL,
  `moi_cost` int NOT NULL,
  `moi_moc_seq` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `menu_option_info`
--

INSERT INTO `menu_option_info` (`moi_seq`, `moi_name`, `moi_cost`, `moi_moc_seq`) VALUES
(1, 'Tall', 0, 1),
(2, 'Grande', 500, 1),
(3, 'Venti', 1000, 1),
(4, '에스프레소 샷', 600, 2),
(5, '바닐라 시럽', 600, 3),
(6, '헤이즐넛 시럽', 600, 3),
(7, '카라멜 시럽', 600, 3),
(8, '얼음 적게', 0, 4),
(9, '얼음 많이', 0, 4),
(10, '우유 휘핑', 600, 5),
(11, '에스프레소 휘핑', 600, 5),
(12, '자바칩', 600, 6),
(13, '자바칩&토핑(반반)', 600, 6),
(14, '통 자바칩 토핑', 600, 6),
(15, '카라멜 드리즐', 600, 7),
(16, '초콜렛 드리즐', 600, 7);

-- --------------------------------------------------------

--
-- 테이블 구조 `menu_qr_image`
--

CREATE TABLE `menu_qr_image` (
  `mqi_seq` int NOT NULL,
  `mqi_image_file` text NOT NULL,
  `mqi_uri` text NOT NULL,
  `mqi_mbi_seq` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `menu_qr_image`
--

INSERT INTO `menu_qr_image` (`mqi_seq`, `mqi_image_file`, `mqi_uri`, `mqi_mbi_seq`) VALUES
(1, 'hot_americano.jpg', 'hot_americano', 1),
(2, 'ice_americano.jpg', 'ice_americano', 2),
(3, 'espresso.jpg', 'espresso', 3),
(4, 'hot_caffe_latte.jpg', 'hot_caffe_latte', 4),
(5, 'ice_caffe_latte.jpg', 'ice_caffe_latte', 5),
(6, 'hot_caffe_mocha.jpg', 'hot_caffe_mocha', 6),
(7, 'ice_caffe_mocha.jpg', 'ice_caffe_mocha', 7),
(8, 'hot_dolce_latte.jpg', 'hot_dolce_latte', 8),
(9, 'ice_dolce_latte.jpg', 'ice_dolce_latte', 9),
(10, 'hot_caramel_macchiato.jpg', 'hot_caramel_macchiato', 10),
(11, 'ice_caramel_macchiato.jpg', 'ice_caramel_macchiato', 11),
(12, 'espresso_frappuccino.jpg', 'espresso_frappuccino', 12),
(13, 'double_espresso_chip_frappuccino.jpg', 'double_espresso_chip_frappuccino', 13),
(14, 'java_chip_frappuccino.jpg', 'java_chip_frappuccino', 14),
(15, 'mocha_frappuccino.jpg', 'mocha_frappuccino', 15),
(16, 'white_chocolate_mocha_frappuccino.jpg', 'white_chocolate_mocha_frappuccino', 16),
(17, 'vanilla_cream_frappuccino.jpg', 'vanilla_cream_frappuccino', 17),
(18, 'malcha_cream_frappuccino.jpg', 'malcha_cream_frappuccino', 18),
(19, 'black_cream_frappuccino.jpg', 'black_cream_frappuccino', 19),
(20, 'redvelvet_cream_cheese_cake.jpg', 'redvelvet_cream_cheese_cake', 20),
(21, 'seven_layer_ganache_cake.jpg', 'seven_layer_ganache_cake', 21),
(22, 'sweetpotato_cream_cake.jpg', 'sweetpotato_cream_cake', 22),
(23, 'cloud_cheese_cake.jpg', 'cloud_cheese_cake', 23),
(24, 'mascarpone_tiramisu_cake.jpg', 'mascarpone_tiramisu_cake', 24),
(25, 'strawberry_mascarpone_cake.jpg', 'strawberry_mascarpone_cake', 25),
(26, 'blueberry_cookie_cheese_cake.jpg', 'blueberry_cookie_cheese_cake', 26),
(27, 'choux_cream_baumkuchen.jpg', 'choux_cream_baumkuchen', 27),
(28, 'vlt_sandwich.jpg', 'vlt_sandwich', 28),
(29, 'belt_sandwich.jpg', 'belt_sandwich', 29),
(30, 'sweet_pumpkin_egg_sandwich.jpg', 'sweet_pumpkin_egg_sandwich', 30),
(31, 'ham_rucola_olive_sandwich.jpg', 'ham_rucola_olive_sandwich', 31),
(32, 'reuben_sandwich.jpg', 'reuben_sandwich', 32),
(33, 'barbeque_chicken_cheese_ciabatta.jpg', 'barbeque_chicken_cheese_ciabatta', 33),
(34, 'egg_sandwich.jpg', 'egg_sandwich', 34),
(35, 'jambon_bagel_sandwich.jpg', 'jambon_bagel_sandwich', 35);

-- --------------------------------------------------------

--
-- Stand-in structure for view `menu_ranking`
-- (See below for the actual view)
--
CREATE TABLE `menu_ranking` (
`cnt` bigint
,`img_file` mediumtext
,`img_uri` mediumtext
,`mbi_name` varchar(64)
,`mbi_seq` int
);

-- --------------------------------------------------------

--
-- 테이블 구조 `productcate_optioncate_connection`
--

CREATE TABLE `productcate_optioncate_connection` (
  `poc_seq` int NOT NULL,
  `poc_pc_seq` int NOT NULL,
  `poc_moc_seq` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `productcate_optioncate_connection`
--

INSERT INTO `productcate_optioncate_connection` (`poc_seq`, `poc_pc_seq`, `poc_moc_seq`) VALUES
(1, 3, 1),
(2, 3, 2),
(3, 3, 3),
(4, 3, 4),
(5, 3, 5),
(6, 3, 6),
(7, 3, 7),
(8, 4, 1),
(9, 4, 2),
(10, 4, 3),
(11, 4, 4),
(12, 4, 5),
(13, 4, 6),
(14, 4, 7);

-- --------------------------------------------------------

--
-- 테이블 구조 `product_category`
--

CREATE TABLE `product_category` (
  `pc_seq` int NOT NULL,
  `pc_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pc_parent_seq` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `product_category`
--

INSERT INTO `product_category` (`pc_seq`, `pc_name`, `pc_parent_seq`) VALUES
(1, '음료', NULL),
(2, '푸드', NULL),
(3, '에스프레소', 1),
(4, '프라푸치노', 1),
(5, '케이크', 2),
(6, '샌드위치', 2);

-- --------------------------------------------------------

--
-- 테이블 구조 `shopping_basket`
--

CREATE TABLE `shopping_basket` (
  `sb_seq` int NOT NULL,
  `sb_status` int DEFAULT '1',
  `sb_order_date` date DEFAULT NULL,
  `sb_request` text,
  `sb_receive` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `sb_payment` int DEFAULT NULL,
  `sb_number` int DEFAULT NULL,
  `sb_smc_seq` int NOT NULL,
  `sb_mi_seq` int NOT NULL,
  `sb_order_number` int DEFAULT NULL,
  `sb_basket_price` int DEFAULT NULL,
  `sb_menu_image_name` text,
  `sb_menu_image_uri` text,
  `sb_menu_total_price` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `shopping_basket`
--

INSERT INTO `shopping_basket` (`sb_seq`, `sb_status`, `sb_order_date`, `sb_request`, `sb_receive`, `sb_payment`, `sb_number`, `sb_smc_seq`, `sb_mi_seq`, `sb_order_number`, `sb_basket_price`, `sb_menu_image_name`, `sb_menu_image_uri`, `sb_menu_total_price`) VALUES
(1, 3, '2023-01-12', '요청합니닷', '받아오겠습니닷', 1, 1, 62, 26, NULL, NULL, NULL, NULL, NULL),
(68, 3, '2023-01-27', 'asdf', 'asdf', 1, 1, 63, 26, 9588932, NULL, NULL, NULL, NULL),
(69, 3, '2023-01-27', 'asdf', 'asdf', 1, 1, 63, 26, 4555135, NULL, NULL, NULL, NULL),
(82, 3, '2023-01-27', 'asdf', 'asdf', 1, 1, 12, 50, 5868212, NULL, NULL, NULL, NULL),
(83, 3, '2023-01-27', 'asdf', 'asdf', 1, 1, 62, 26, 9090109, NULL, NULL, NULL, NULL),
(84, 3, '2023-01-27', 'asdf', 'asdf', 1, 1, 62, 26, 7183613, NULL, NULL, NULL, NULL),
(93, 2, '2023-01-30', 'asdf', 'asdf', 1, 1, 62, 26, 4056349, 5100, NULL, NULL, NULL),
(94, 2, '2023-01-30', 'asdf', 'asdf', 1, 1, 12, 26, 5971658, 4500, NULL, NULL, NULL),
(95, 2, '2023-01-31', 'asdf', 'asdf', 1, 1, 12, 26, 6358218, NULL, NULL, NULL, NULL),
(96, 2, '2023-01-31', 'asdf', 'asdf', 1, 1, 12, 26, 5424865, NULL, NULL, NULL, NULL),
(97, 2, '2023-01-31', 'asdf', 'asdf', 1, 1, 12, 26, 3287401, 6500, NULL, NULL, NULL),
(98, 3, '2023-01-31', 'asdf', NULL, 1, 1, 12, 26, 7723029, 6500, NULL, NULL, NULL),
(99, 3, '2023-01-31', '요청', NULL, 1, 1, 12, 26, 4000605, 4000, NULL, NULL, NULL),
(100, 3, '2023-01-31', '요청', '실험용', 1, 1, 12, 26, 6039490, 4000, NULL, NULL, NULL),
(101, 3, '2023-01-31', '요청', NULL, 1, 1, 12, 26, 4380143, 4000, NULL, NULL, NULL),
(102, 3, '2023-01-31', '요청', NULL, 1, 1, 12, 26, 1730573, 4000, NULL, NULL, NULL),
(103, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 5710365, 4000, NULL, NULL, NULL),
(104, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 865570, 4000, NULL, NULL, NULL),
(105, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 7822903, 4000, NULL, NULL, NULL),
(106, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 4287036, 4000, NULL, NULL, NULL),
(107, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 2809455, 4000, NULL, NULL, NULL),
(108, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 7297581, 4000, NULL, NULL, NULL),
(109, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 4617468, 4000, NULL, NULL, NULL),
(110, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 4020344, 4000, NULL, NULL, NULL),
(111, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 7689395, 4000, NULL, NULL, NULL),
(112, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 4203228, 4000, NULL, NULL, NULL),
(114, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 2686038, 4000, NULL, NULL, NULL),
(115, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 2358077, 4000, NULL, NULL, NULL),
(116, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 5918856, 4000, NULL, NULL, NULL),
(117, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 6538645, 4000, NULL, NULL, NULL),
(118, 3, '2023-01-31', '요청', '수령', 1, 1, 12, 26, 3638737, 4000, NULL, NULL, NULL),
(119, 3, '2023-02-01', '요청', '수령', 1, 1, 12, 26, 1519710, 4000, NULL, NULL, NULL),
(120, 3, '2023-02-01', '요청', '수령', 1, 1, 12, 26, 271320, 4000, NULL, NULL, NULL),
(121, 3, '2023-02-01', '요청', '수령', 1, 1, 12, 26, 9837010, 4000, NULL, NULL, NULL),
(122, 3, '2023-02-01', '요청', '수령', 1, 1, 12, 26, 7355345, 4000, NULL, NULL, NULL),
(123, 3, '2023-02-01', '요청', '수령', 1, 1, 12, 26, 8817329, 4000, NULL, NULL, NULL),
(124, 3, '2023-02-01', '요청', '수령', 1, 1, 12, 26, 3221440, 4000, NULL, NULL, NULL),
(125, 3, '2023-02-01', '요청', '수령', 1, 1, 1, 26, 7308246, 4500, NULL, NULL, NULL),
(126, 3, '2023-02-01', '요청', '수령', 1, 1, 1, 26, 5608745, 4500, 'Menu_1675136060494.jpg', 'hot_americano', NULL),
(127, 3, '2023-02-01', '요청', '수령', 1, 1, 1, 26, 7254983, 4500, 'Menu_1675136060494.jpg', 'hot_americano', NULL),
(128, 3, '2023-02-01', '요청', '수령', 1, 1, 12, 26, 5578417, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', NULL),
(129, 3, '2023-02-01', '요청', '수령', 1, 1, 2, 26, 5838119, 4500, 'Menu_1675136761747.jpg', 'ice_americano', NULL),
(130, 3, '2023-02-01', '요청', '수령', 1, 1, 2, 26, 663597, 4500, 'Menu_1675136761747.jpg', 'ice_americano', NULL),
(131, 3, '2023-02-01', '요청', '수령', 1, 1, 2, 26, 9114644, 4500, NULL, NULL, NULL),
(132, 3, '2023-02-01', '요청', '수령', 1, 1, 2, 26, 141000, 4500, NULL, NULL, NULL),
(133, 3, '2023-02-01', '요청', '수령', 1, 1, 2, 26, 3297938, 4500, NULL, NULL, NULL),
(134, 3, '2023-02-01', '요청', '수령', 1, 1, 2, 26, 8969268, 4500, NULL, NULL, NULL),
(135, 3, '2023-02-01', '요청', '수령', 1, 1, 2, 26, 4854461, 4500, 'Menu_1675136761747.jpg', 'ice_americano', NULL),
(136, 3, '2023-02-01', '요청', '수령', 1, 1, 2, 26, 5992239, 4500, 'Menu_1675136761747.jpg', 'ice_americano', NULL),
(137, 3, '2023-02-02', '요청', '수령', 1, 1, 2, 26, 5446838, 4500, 'Menu_1675136761747.jpg', 'ice_americano', NULL),
(138, 3, '2023-02-02', '요청', '수령', 1, 1, 2, 26, 4556454, 4500, 'Menu_1675136761747.jpg', 'ice_americano', NULL),
(143, 3, '2023-02-02', '요청', '수령', 1, 1, 2, 26, 9967059, 4500, 'Menu_1675136761747.jpg', 'ice_americano', NULL),
(144, 3, '2023-02-02', '요청', '수령', 1, 1, 2, 26, 2803529, 4500, 'Menu_1675136761747.jpg', 'ice_americano', NULL),
(145, 3, '2023-02-02', '요청', '수령', 1, 1, 2, 26, 8736133, 4500, 'Menu_1675136761747.jpg', 'ice_americano', NULL),
(148, 3, '2023-02-02', '요청', '수령', 1, 1, 2, 26, 4050855, 4500, 'Menu_1675136761747.jpg', 'ice_americano', NULL),
(149, 3, '2023-02-02', '요청', '수령', 1, 1, 12, 26, 4581704, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', NULL),
(156, 3, '2023-02-02', '요청', '수령', 1, 1, 3, 26, 6283780, 4000, 'Menu_1675136812899.jpg', 'espresso', NULL),
(157, 3, '2023-02-02', '요청', '수령', 1, 1, 3, 26, 46075, 4000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(158, 3, '2023-02-02', '요청', '수령', 1, 1, 3, 26, 9115008, 4000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(159, 3, '2023-02-02', '요청', '수령', 1, 1, 3, 26, 8214772, 4000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(160, 3, '2023-02-02', '요청', '수령', 1, 1, 3, 26, 61197, 4000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(161, 3, '2023-02-02', '요청', '수령', 1, 1, 3, 26, 417273, 4000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(162, 3, '2023-02-03', '요청', '수령', 1, 1, 3, 26, 2660768, 4000, 'Menu_1675136812899.jpg', 'espresso', 4500),
(204, 3, '2023-02-03', '요청', '수령', 1, 1, 12, 26, 716604, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', NULL),
(209, 3, '2023-02-03', '요청', '수령', 1, 3, 1, 70, 5253985, 13500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(210, 3, '2023-02-03', '요청', '수령', 1, 1, 12, 26, 1906345, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', NULL),
(211, 3, '2023-02-03', '요청', '수령', 1, 1, 1, 70, 3126119, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(212, 3, '2023-02-03', '요청', '수령', 1, 1, 12, 26, 9889115, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', NULL),
(213, 3, '2023-02-03', '요청', '수령', 1, 2, 3, 70, 8797741, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(214, 3, '2023-02-03', '요청', '수령', 1, 1, 1, 70, 8307051, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(215, 3, '2023-02-03', '요청', '수령', 1, 1, 2, 70, 6228555, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(217, 3, '2023-02-03', '요청', '수령', 1, 1, 1, 70, 1446851, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(218, 3, '2023-02-03', '요청', '수령', 1, 2, 3, 70, 6420583, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(219, 3, '2023-02-03', '요청', '수령', 1, 1, 12, 26, 3599154, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 5900),
(220, 3, '2023-02-03', '요청', '수령', 1, 1, 12, 70, 3655505, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 5900),
(228, 3, '2023-02-03', '요청', '수령', 1, 2, 2, 70, 3453388, 9000, 'Menu_1675136761747.jpg', 'ice_americano', 9000),
(229, 3, '2023-02-03', '요청', '수령', 1, 1, 12, 70, 4252647, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 5900),
(230, 3, '2023-02-03', '요청', '수령', 1, 3, 1, 70, 1507229, 13500, 'Menu_1675136060494.jpg', 'hot_americano', 13500),
(231, 3, '2023-02-03', '요청', '수령', 1, 1, 2, 70, 6060715, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(233, 3, '2023-02-03', '요청', '수령', 1, 1, 1, 70, 8612729, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(234, 3, '2023-02-03', '요청', '수령', 1, 1, 2, 70, 2201654, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(235, 3, '2023-02-03', '요청', '수령', 1, 1, 6, 70, 5289405, 5500, 'Menu_1675136990432.jpg', 'hot_caffe_mocha', 5500),
(237, 3, '2023-02-03', '요청', '수령', 1, 1, 1, 70, 8677909, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(238, 3, '2023-02-03', '요청', '수령', 1, 1, 3, 70, 7925400, 4000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(243, 3, '2023-02-03', '요청', '수령', 1, 2, 3, 26, 7648773, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(257, 3, '2023-02-03', '', '1', 1, 1, 6, 70, 9092566, 5500, 'Menu_1675136990432.jpg', 'hot_caffe_mocha', 5500),
(258, 3, '2023-02-03', '', '1', 1, 3, 1, 70, 4140805, 13500, 'Menu_1675136060494.jpg', 'hot_americano', 15000),
(265, 3, '2023-02-04', '', '1', 1, 4, 3, 84, 4924764, 16000, 'Menu_1675136812899.jpg', 'espresso', 4500),
(267, 3, '2023-02-03', '요청', '수령', 1, 1, 12, 26, 6122880, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 5900),
(268, 3, '2023-02-03', '요청', '수령', 1, 1, 12, 26, 333960, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 5900),
(269, 3, '2023-02-03', '요청', '수령', 1, 2, 3, 26, 2079047, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(270, 3, '2023-02-03', '요청', '수령', 1, 1, 12, 26, 6593309, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 5900),
(271, 3, '2023-02-03', '요청', '수령', 1, 2, 3, 26, 1590113, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(273, 3, '2023-02-03', '', '1', 1, 1, 2, 70, 4602961, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(274, 3, '2023-02-03', '', '1', 1, 1, 5, 70, 5383129, 5000, 'Menu_1675136897390.jpg', 'ice_caffe_latte', 5000),
(276, 3, '2023-02-03', '요청', '수령', 1, 2, 3, 26, 7664358, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(294, 1, NULL, NULL, NULL, NULL, 1, 2, 83, 5541526, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(297, 1, NULL, NULL, NULL, NULL, 1, 2, 83, 5907393, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(298, 1, NULL, NULL, NULL, NULL, 2, 1, 83, 4241019, 9000, 'Menu_1675136060494.jpg', 'hot_americano', 9000),
(304, 1, NULL, NULL, NULL, NULL, 4, 1, 83, 7987199, 18000, 'Menu_1675136060494.jpg', 'hot_americano', 20000),
(334, 3, '2023-02-03', '', '1', 1, 5, 1, 70, 2242165, 22500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(335, 3, '2023-02-03', '', '1', 1, 2, 2, 70, 8884725, 9000, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(339, 3, '2023-02-03', '', '1', 1, 2, 2, 70, 223412, 9000, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(340, 3, '2023-02-03', '', '1', 1, 1, 1, 70, 1429253, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(341, 3, '2023-02-03', '', '1', 1, 1, 1, 70, 3030986, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(342, 3, '2023-02-03', '', '1', 1, 1, 1, 70, 8462342, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(343, 3, '2023-02-03', '', '1', 1, 1, 2, 70, 9989296, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(344, 3, '2023-02-03', '', '1', 1, 1, 3, 70, 5860596, 4000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(345, 3, '2023-02-03', '', '1', 1, 1, 1, 88, 8343189, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(346, 3, '2023-02-03', '', '1', 1, 1, 2, 88, 3389937, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(347, 3, '2023-02-04', '', '1', 1, 1, 3, 84, 2109453, 4000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(348, 3, '2023-02-03', '', '1', 1, 2, 1, 88, 8522903, 9000, 'Menu_1675136060494.jpg', 'hot_americano', 10000),
(349, 3, '2023-02-03', '', '1', 1, 1, 1, 88, 8440823, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(351, 3, '2023-02-03', '', '1', 1, 3, 3, 88, 5468595, 12000, 'Menu_1675136812899.jpg', 'espresso', 4500),
(352, 3, '2023-02-03', '', '1', 1, 1, 1, 88, 66860, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 5000),
(353, 3, '2023-02-03', '', '1', 1, 2, 3, 88, 5794559, 8000, 'Menu_1675136812899.jpg', 'espresso', 9000),
(354, 3, '2023-02-03', '', '1', 1, 1, 1, 88, 8710756, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(364, 3, '2023-02-03', '', '1', 1, 3, 4, 88, 6609118, 15000, 'Menu_1675136863636.jpg', 'hot_caffe_latte', 5500),
(365, 3, '2023-02-03', '', '1', 1, 1, 2, 88, 6704811, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(366, 3, '2023-02-03', '', '1', 1, 1, 1, 88, 4391119, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(367, 3, '2023-02-03', '', '1', 1, 3, 1, 88, 3684993, 13500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(369, 3, '2023-02-04', '', '1', 1, 3, 3, 88, 7965755, 12000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(370, 3, '2023-02-04', '', '1', 1, 1, 4, 88, 2740466, 5000, 'Menu_1675136863636.jpg', 'hot_caffe_latte', 5000),
(371, 3, '2023-02-04', '', '1', 1, 1, 3, 84, 1325806, 4000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(372, 3, '2023-02-04', '', '1', 1, 1, 3, 84, 4000819, 4000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(373, 3, '2023-02-04', '', '1', 1, 1, 2, 88, 7426335, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(374, 3, '2023-02-04', '', '1', 1, 1, 1, 88, 5052677, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(375, 3, '2023-02-04', '', '1', 1, 1, 1, 88, 3824004, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(376, 3, '2023-02-04', '', '1', 1, 1, 1, 88, 2065834, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(377, 3, '2023-02-04', '', '1', 1, 1, 1, 84, 5191910, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(378, 3, '2023-02-04', '', '1', 1, 1, 2, 84, 7096960, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(379, 3, '2023-02-04', '감사', '감사', 1, 1, 2, 88, 9978504, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(380, 3, '2023-02-04', '요청', '수령', 1, 3, 12, 26, 9164371, 17700, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 17700),
(381, 3, '2023-02-04', '요청', '수령', 1, 3, 12, 26, 9293301, 17700, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 17700),
(382, 3, '2023-02-04', '요청', '수령', 1, 3, 12, 26, 7355188, 17700, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 17700),
(383, 3, '2023-02-04', '요청', '수령', 1, 3, 12, 26, 6341566, 17700, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 17700),
(384, 3, '2023-02-04', '요청', '수령', 1, 3, 12, 26, 660105, 17700, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 17700),
(385, 3, '2023-02-04', '', '1', 1, 1, 1, 88, 4928167, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(386, 3, '2023-02-04', '', '1', 1, 1, 2, 88, 3379296, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(387, 3, '2023-02-04', '', '1', 1, 1, 1, 88, 1957198, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(388, 3, '2023-02-04', '', '1', 1, 1, 2, 88, 3808699, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(389, 3, '2023-02-04', '', '1', 1, 1, 2, 88, 6375610, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(414, 3, '2023-02-04', '', '1', 1, 1, 1, 88, 39478, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(415, 3, '2023-02-04', '', '1', 1, 1, 21, 88, 818441, 4900, 'Menu_1675142698590.jpg', 'sweet_pumpkin_egg_sandwich', 4900),
(416, 3, '2023-02-04', '', '1', 1, 1, 20, 88, 9660064, 5900, 'Menu_1675142650889.jpg', 'belt_sandwich', 5900),
(417, 3, '2023-02-04', '요청', '수령', 1, 2, 3, 26, 4205264, 8000, 'Menu_1675136812899.jpg', 'espresso', 8000),
(419, 3, '2023-02-04', '', '1', 1, 1, 21, 88, 7672398, 4900, 'Menu_1675142698590.jpg', 'sweet_pumpkin_egg_sandwich', 4900),
(420, 3, '2023-02-06', '요청', '수령', 1, 2, 3, 26, 9026908, 8000, 'Menu_1675136812899.jpg', 'espresso', 8000),
(421, 3, '2023-02-06', '요청', '수령', 1, 2, 3, 26, 8037709, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(422, 3, '2023-02-06', '요청', '수령', 1, 2, 3, 26, 2616113, 8000, 'Menu_1675136812899.jpg', 'espresso', 8000),
(423, 3, '2023-02-04', '', '1', 1, 1, 1, 88, 6446343, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 5000),
(424, 3, '2023-02-04', '', '1', 1, 1, 2, 88, 3805855, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(425, 3, '2023-02-05', '', '1', 1, 1, 1, 88, 1056675, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(426, 3, '2023-02-05', '', '1', 1, 1, 2, 88, 407296, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(427, 3, '2023-02-05', '', '1', 1, 1, 2, 88, 3739567, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 5000),
(428, 3, '2023-02-05', '', '1', 1, 1, 3, 88, 3829653, 4000, 'Menu_1675136812899.jpg', 'espresso', 4500),
(429, 3, '2023-02-05', '', '1', 1, 1, 1, 89, 3802034, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(430, 3, '2023-02-05', '', '1', 1, 1, 1, 89, 9456815, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(433, 3, '2023-02-05', '', '1', 1, 5, 3, 89, 3336466, 20000, 'Menu_1675136812899.jpg', 'espresso', 5000),
(436, 3, '2023-02-05', '', '1', 1, 1, 1, 89, 118266, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 5000),
(437, 3, '2023-02-05', '', '1', 1, 1, 1, 88, 9368894, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(438, 3, '2023-02-05', '', '1', 1, 1, 5, 88, 4232502, 5000, 'Menu_1675136897390.jpg', 'ice_caffe_latte', 5000),
(475, 3, '2023-02-05', '', '1', 1, 1, 2, 88, 7748151, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(476, 1, NULL, NULL, NULL, NULL, 1, 2, 91, 9481231, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(477, 3, '2023-02-05', '', '1', 1, 1, 1, 88, 9483600, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(478, 3, '2023-02-05', '', '1', 1, 1, 1, 88, 9752260, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(479, 3, '2023-02-05', '', '1', 1, 1, 4, 88, 8275718, 5000, 'Menu_1675136863636.jpg', 'hot_caffe_latte', 5000),
(480, 3, '2023-02-06', '', '1', 1, 1, 1, 88, 5251183, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 5000),
(481, 3, '2023-02-05', '', '1', 1, 1, 2, 89, 5159603, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 5000),
(482, 3, '2023-02-06', '', '1', 1, 1, 1, 88, 6403938, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 5000),
(483, 3, '2023-02-06', '요청', '수령', 1, 2, 3, 26, 3399336, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(484, 3, '2023-02-06', '', '1', 1, 1, 2, 84, 3367739, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(485, 3, '2023-02-06', '', '1', 1, 1, 3, 84, 7592161, 4000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(486, 3, '2023-02-06', '요청', '수령', 1, 2, 3, 26, 3999134, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(487, 3, '2023-02-06', '요청', '수령', 1, 2, 3, 26, 5436866, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(488, 3, '2023-02-06', '요청', '수령', 1, 2, 3, 26, 6627782, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(489, 3, '2023-02-06', '요청', '수령', 1, 2, 3, 26, 1381253, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(490, 3, '2023-02-06', '요청', '수령', 1, 2, 3, 26, 5610474, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(491, 3, '2023-02-06', '요청', '수령', 1, 3, 12, 26, 863433, 17700, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 17700),
(492, 3, '2023-02-06', '요청', '수령', 1, 3, 12, 26, 6904646, 17700, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 17700),
(493, 3, '2023-02-06', '요청', '수령', 1, 3, 12, 26, 3044826, 17700, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 17700),
(494, 3, '2023-02-06', '요청', '수령', 1, 3, 12, 26, 7677471, 17700, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 17700),
(495, 3, '2023-02-06', '요청', '수령', 1, 2, 3, 26, 9197724, 8000, 'Menu_1675136812899.jpg', 'espresso', 8500),
(496, 3, '2023-02-06', '요청', '수령', 1, 3, 12, 26, 1520223, 17700, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 17700),
(497, 3, '2023-02-06', '', '1', 1, 1, 1, 84, 9656935, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(498, 3, '2023-02-06', '', '1', 1, 1, 2, 84, 962654, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(499, 3, '2023-02-06', '', '1', 1, 1, 2, 88, 2245144, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(500, 1, NULL, NULL, NULL, NULL, 1, 12, 84, 550461, 5900, 'Menu_1675137794140.jpg', 'mocha_frappuccino', 5900),
(501, 1, NULL, NULL, NULL, NULL, 1, 2, 84, 3831965, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 4500),
(502, 1, NULL, NULL, NULL, NULL, 1, 1, 84, 5620132, 4500, 'Menu_1675136060494.jpg', 'hot_americano', 4500),
(503, 3, '2023-02-06', '', '1', 1, 3, 3, 88, 9417557, 12000, 'Menu_1675136812899.jpg', 'espresso', 4000),
(505, 3, '2023-02-06', '', '1', 1, 1, 2, 88, 2310783, 4500, 'Menu_1675136761747.jpg', 'ice_americano', 5000);

-- --------------------------------------------------------

--
-- 테이블 구조 `shopping_basket_option`
--

CREATE TABLE `shopping_basket_option` (
  `sbo_seq` int NOT NULL,
  `sbo_number` int NOT NULL DEFAULT '1',
  `sbo_moi_seq` int NOT NULL,
  `sbo_sb_seq` int NOT NULL,
  `sbo_option_order_number` int DEFAULT NULL,
  `sbo_option_price` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `shopping_basket_option`
--

INSERT INTO `shopping_basket_option` (`sbo_seq`, `sbo_number`, `sbo_moi_seq`, `sbo_sb_seq`, `sbo_option_order_number`, `sbo_option_price`) VALUES
(204, 1, 1, 93, 4056349, 0),
(205, 1, 4, 93, 4056349, 600),
(206, 1, 8, 93, 4056349, 0),
(207, 1, 1, 94, 5971658, 0),
(208, 1, 4, 94, 5971658, 600),
(209, 1, 8, 94, 5971658, 0),
(210, 1, 1, 95, 6358218, NULL),
(211, 1, 4, 95, 6358218, NULL),
(212, 1, 8, 95, 6358218, NULL),
(213, 1, 1, 96, 5424865, NULL),
(214, 1, 4, 96, 5424865, NULL),
(215, 1, 8, 96, 5424865, NULL),
(216, 1, 1, 97, 3287401, 0),
(217, 1, 4, 97, 3287401, 600),
(218, 1, 8, 97, 3287401, 0),
(219, 1, 1, 98, 7723029, 0),
(220, 1, 4, 98, 7723029, 600),
(221, 1, 8, 98, 7723029, 0),
(222, 1, 1, 99, 4000605, 0),
(223, 1, 4, 99, 4000605, 600),
(224, 1, 8, 99, 4000605, 0),
(225, 1, 1, 102, 1730573, 0),
(226, 1, 4, 102, 1730573, 600),
(227, 1, 8, 102, 1730573, 0),
(228, 1, 1, 103, 5710365, 0),
(229, 1, 4, 103, 5710365, 600),
(230, 1, 8, 103, 5710365, 0),
(231, 1, 1, 104, 865570, 0),
(232, 1, 4, 104, 865570, 600),
(233, 1, 8, 104, 865570, 0),
(234, 1, 1, 105, 7822903, 0),
(235, 1, 4, 105, 7822903, 600),
(236, 1, 8, 105, 7822903, 0),
(237, 1, 1, 106, 4287036, 0),
(238, 1, 4, 106, 4287036, 600),
(239, 1, 8, 106, 4287036, 0),
(240, 1, 1, 107, 2809455, 0),
(241, 1, 4, 107, 2809455, 600),
(242, 1, 8, 107, 2809455, 0),
(243, 1, 1, 108, 7297581, 0),
(244, 1, 4, 108, 7297581, 600),
(245, 1, 8, 108, 7297581, 0),
(246, 1, 1, 109, 4617468, 0),
(247, 1, 4, 109, 4617468, 600),
(248, 1, 8, 109, 4617468, 0),
(249, 1, 1, 110, 4020344, 0),
(250, 1, 4, 110, 4020344, 600),
(251, 1, 8, 110, 4020344, 0),
(252, 1, 1, 111, 7689395, 0),
(253, 1, 4, 111, 7689395, 600),
(254, 1, 8, 111, 7689395, 0),
(255, 1, 1, 112, 4203228, 0),
(256, 1, 4, 112, 4203228, 600),
(257, 1, 8, 112, 4203228, 0),
(261, 1, 1, 114, 2686038, 0),
(262, 1, 4, 114, 2686038, 600),
(263, 1, 8, 114, 2686038, 0),
(264, 1, 1, 115, 2358077, 0),
(265, 1, 4, 115, 2358077, 600),
(266, 1, 8, 115, 2358077, 0),
(267, 1, 1, 116, 5918856, 0),
(268, 1, 4, 116, 5918856, 600),
(269, 1, 8, 116, 5918856, 0),
(270, 1, 1, 117, 6538645, 0),
(271, 1, 4, 117, 6538645, 600),
(272, 1, 8, 117, 6538645, 0),
(273, 1, 1, 118, 3638737, 0),
(274, 1, 4, 118, 3638737, 600),
(275, 1, 8, 118, 3638737, 0),
(276, 1, 1, 119, 1519710, 0),
(277, 1, 4, 119, 1519710, 600),
(278, 1, 8, 119, 1519710, 0),
(279, 1, 1, 121, 9837010, 0),
(280, 1, 4, 121, 9837010, 600),
(281, 1, 8, 121, 9837010, 0),
(282, 1, 1, 122, 7355345, 0),
(283, 1, 4, 122, 7355345, 600),
(284, 1, 8, 122, 7355345, 0),
(285, 1, 1, 123, 8817329, 0),
(286, 1, 4, 123, 8817329, 600),
(287, 1, 8, 123, 8817329, 0),
(288, 1, 1, 124, 3221440, 0),
(289, 1, 4, 124, 3221440, 600),
(290, 1, 8, 124, 3221440, 0),
(291, 1, 1, 125, 7308246, 0),
(292, 1, 4, 125, 7308246, 600),
(293, 1, 8, 125, 7308246, 0),
(294, 1, 1, 126, 5608745, 0),
(295, 1, 4, 126, 5608745, 600),
(296, 1, 8, 126, 5608745, 0),
(297, 1, 1, 127, 7254983, 0),
(298, 1, 4, 127, 7254983, 600),
(299, 1, 8, 127, 7254983, 0),
(300, 1, 1, 129, 5838119, 0),
(301, 1, 4, 129, 5838119, 600),
(302, 1, 8, 129, 5838119, 0),
(303, 1, 1, 130, 663597, 0),
(304, 1, 4, 130, 663597, 600),
(305, 1, 8, 130, 663597, 0),
(306, 1, 1, 131, 9114644, 0),
(307, 1, 4, 131, 9114644, 600),
(308, 1, 8, 131, 9114644, 0),
(309, 1, 1, 132, 141000, 0),
(310, 1, 4, 132, 141000, 600),
(311, 1, 8, 132, 141000, 0),
(312, 1, 1, 133, 3297938, 0),
(313, 1, 4, 133, 3297938, 600),
(314, 1, 8, 133, 3297938, 0),
(315, 1, 1, 134, 8969268, 0),
(316, 1, 4, 134, 8969268, 600),
(317, 1, 8, 134, 8969268, 0),
(318, 1, 1, 135, 4854461, 0),
(319, 1, 4, 135, 4854461, 600),
(320, 1, 8, 135, 4854461, 0),
(321, 1, 1, 136, 5992239, 0),
(322, 1, 4, 136, 5992239, 600),
(323, 1, 8, 136, 5992239, 0),
(324, 1, 1, 137, 5446838, 0),
(325, 1, 4, 137, 5446838, 600),
(326, 1, 8, 137, 5446838, 0),
(327, 1, 1, 138, 4556454, 0),
(328, 1, 4, 138, 4556454, 600),
(329, 1, 8, 138, 4556454, 0),
(334, 1, 1, 143, 9967059, 0),
(335, 1, 4, 143, 9967059, 600),
(336, 1, 8, 143, 9967059, 0),
(337, 1, 1, 144, 2803529, 0),
(338, 1, 4, 144, 2803529, 600),
(339, 1, 8, 144, 2803529, 0),
(340, 1, 1, 145, 8736133, 0),
(341, 1, 4, 145, 8736133, 600),
(342, 1, 8, 145, 8736133, 0),
(346, 1, 1, 148, 4050855, 0),
(347, 1, 4, 148, 4050855, 600),
(348, 1, 8, 148, 4050855, 0),
(357, 1, 1, 156, 6283780, 0),
(358, 1, 1, 157, 46075, 0),
(359, 1, 1, 158, 9115008, 0),
(360, 1, 1, 159, 8214772, 0),
(361, 1, 1, 160, 61197, 0),
(362, 1, 1, 161, 417273, 0),
(363, 1, 2, 162, 2660768, 500),
(372, 2, 2, 171, 7969233, 1000),
(410, 1, 1, 209, 5253985, 0),
(411, 1, 1, 211, 3126119, 0),
(412, 1, 2, 213, 8797741, 500),
(413, 1, 5, 213, 8797741, 600),
(414, 1, 1, 214, 8307051, 0),
(415, 1, 1, 215, 6228555, 0),
(418, 1, 1, 217, 1446851, 0),
(419, 1, 2, 218, 6420583, 500),
(420, 1, 5, 218, 6420583, 600),
(427, 2, 1, 228, 3453388, 0),
(428, 3, 1, 230, 1507229, 0),
(429, 1, 1, 231, 6060715, 0),
(430, 1, 1, 233, 8612729, 0),
(431, 1, 1, 234, 2201654, 0),
(432, 1, 1, 235, 5289405, 0),
(433, 1, 1, 237, 8677909, 0),
(434, 1, 1, 238, 7925400, 0),
(438, 1, 2, 243, 7648773, 500),
(439, 1, 5, 243, 7648773, 600),
(453, 1, 1, 257, 9092566, 0),
(454, 3, 2, 258, 4140805, 1500),
(456, 1, 1, 260, 6626785, 0),
(461, 1, 2, 265, 4924764, 500),
(463, 1, 2, 269, 2079047, 500),
(464, 1, 5, 269, 2079047, 600),
(465, 1, 2, 271, 1590113, 500),
(466, 1, 5, 271, 1590113, 600),
(468, 1, 1, 273, 4602961, 0),
(469, 1, 1, 274, 5383129, 0),
(471, 1, 2, 276, 7664358, 500),
(472, 1, 5, 276, 7664358, 600),
(490, 1, 1, 294, 5541526, 0),
(493, 1, 1, 297, 5907393, 0),
(494, 2, 1, 298, 4241019, 0),
(499, 4, 2, 304, 7987199, 2000),
(529, 1, 1, 334, 2242165, 0),
(530, 1, 1, 335, 8884725, 0),
(534, 1, 1, 339, 223412, 0),
(535, 1, 1, 340, 1429253, 0),
(536, 1, 1, 341, 3030986, 0),
(537, 1, 1, 342, 8462342, 0),
(538, 1, 1, 343, 9989296, 0),
(539, 1, 1, 344, 5860596, 0),
(540, 1, 1, 345, 8343189, 0),
(541, 1, 1, 346, 3389937, 0),
(542, 1, 1, 347, 2109453, 0),
(543, 2, 2, 348, 8522903, 1000),
(544, 1, 1, 349, 8440823, 0),
(546, 1, 2, 351, 5468595, 500),
(547, 1, 2, 352, 66860, 500),
(548, 2, 2, 353, 5794559, 1000),
(549, 1, 1, 354, 8710756, 0),
(559, 1, 2, 364, 6609118, 500),
(560, 1, 1, 365, 6704811, 0),
(561, 1, 1, 366, 4391119, 0),
(562, 1, 1, 367, 3684993, 0),
(564, 1, 1, 369, 7965755, 0),
(565, 1, 1, 370, 2740466, 0),
(566, 1, 1, 371, 1325806, 0),
(567, 1, 1, 372, 4000819, 0),
(568, 1, 1, 373, 7426335, 0),
(569, 1, 1, 374, 5052677, 0),
(570, 1, 1, 375, 3824004, 0),
(571, 1, 1, 376, 2065834, 0),
(572, 1, 1, 377, 5191910, 0),
(573, 1, 1, 378, 7096960, 0),
(574, 1, 1, 379, 9978504, 0),
(575, 1, 1, 385, 4928167, 0),
(576, 1, 1, 386, 3379296, 0),
(577, 1, 1, 387, 1957198, 0),
(578, 1, 1, 388, 3808699, 0),
(579, 1, 1, 389, 6375610, 0),
(589, 1, 1, 414, 39478, 0),
(591, 1, 2, 421, 8037709, 500),
(592, 1, 5, 421, 8037709, 600),
(593, 1, 12, 421, 8037709, 600),
(594, 1, 2, 423, 6446343, 500),
(595, 1, 1, 424, 3805855, 0),
(596, 1, 1, 425, 1056675, 0),
(597, 1, 1, 426, 407296, 0),
(598, 1, 2, 427, 3739567, 500),
(599, 1, 2, 428, 3829653, 500),
(600, 1, 1, 429, 3802034, 0),
(601, 1, 1, 430, 9456815, 0),
(604, 1, 3, 433, 3336466, 1000),
(607, 1, 2, 436, 118266, 500),
(608, 1, 1, 437, 9368894, 0),
(609, 1, 1, 438, 4232502, 0),
(643, 1, 1, 475, 7748151, 0),
(644, 1, 1, 476, 9481231, 0),
(645, 1, 1, 477, 9483600, 0),
(646, 1, 1, 478, 9752260, 0),
(647, 1, 1, 479, 8275718, 0),
(648, 1, 2, 480, 5251183, 500),
(649, 1, 2, 481, 5159603, 500),
(650, 1, 2, 482, 6403938, 500),
(651, 1, 2, 483, 3399336, 500),
(652, 1, 5, 483, 3399336, 600),
(653, 1, 12, 483, 3399336, 600),
(654, 1, 1, 484, 3367739, 0),
(655, 1, 1, 485, 7592161, 0),
(656, 1, 2, 486, 3999134, 500),
(657, 1, 5, 486, 3999134, 600),
(658, 1, 12, 486, 3999134, 600),
(659, 1, 2, 487, 5436866, 500),
(660, 1, 5, 487, 5436866, 600),
(661, 1, 12, 487, 5436866, 600),
(662, 1, 2, 488, 6627782, 500),
(663, 1, 5, 488, 6627782, 600),
(664, 1, 12, 488, 6627782, 600),
(665, 1, 2, 489, 1381253, 500),
(666, 1, 5, 489, 1381253, 600),
(667, 1, 12, 489, 1381253, 600),
(668, 1, 2, 490, 5610474, 500),
(669, 1, 5, 490, 5610474, 600),
(670, 1, 12, 490, 5610474, 600),
(671, 1, 2, 495, 9197724, 500),
(672, 1, 5, 495, 9197724, 600),
(673, 1, 12, 495, 9197724, 600),
(674, 1, 1, 497, 9656935, 0),
(675, 1, 1, 498, 962654, 0),
(676, 1, 1, 499, 2245144, 0),
(677, 1, 1, 500, 550461, 0),
(678, 1, 1, 501, 3831965, 0),
(679, 1, 1, 502, 5620132, 0),
(680, 1, 1, 503, 9417557, 0),
(682, 1, 2, 505, 2310783, 500);

-- --------------------------------------------------------

--
-- 테이블 구조 `store_announcement`
--

CREATE TABLE `store_announcement` (
  `sa_seq` int NOT NULL,
  `sa_img_file` text NOT NULL,
  `sa_uri` text NOT NULL,
  `sa_title` varchar(255) NOT NULL,
  `sa_content` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `store_announcement`
--

INSERT INTO `store_announcement` (`sa_seq`, `sa_img_file`, `sa_uri`, `sa_title`, `sa_content`) VALUES
(1, 'Notice_1674716276389.jpg', 'starbucks_online_recharge', '스타벅스 카드 온라인 재충전 / e-Gift Card 구매 가능 금액', '안녕하세요. 스타벅스 코리아입니다.\n스타벅스 카드 온라인 재충전 및 e-Gift Card 결제수단별 구매 가능 금액에 대해 안내드립니다.'),
(2, 'Notice_1674716685222.jpg', 'starbucks_service_improve', '시스템 개선 및 서비스 점검 안내', '안녕하세요. 스타벅스 코리아입니다.\n\n보다 나은 서비스를 제공하고자 아래와 같이 시스템 서버 작업을 진행합니다.\n\n \n\n- 일자 및 시간: 2023년 1월 25일(수) 23시 30분 ~ 1월 26일(목) 05시 30분(6시간)\n\n- 대상 서비스 : 스타벅스 홈페이지 / 스타벅스 APP / Bixby / 삼성카드 스타벅스 오더 / 신한PayFAN 스타벅스오더 / 네이버 주문 / 스타벅스 현대카드 발급 서비스\n\n- 점검 내용: 시스템 서버 작업\n\n \n\n해당 점검 시간 중 웹/앱 및 외부 채널 서비스 이용이 제한되오니 양해 부탁드리겠습니다.\n\n감사합니다.'),
(3, 'Notice_1674716763436.jpg', 'starbucks_ecoupon_price', '스타벅스 e카드 교환권 등록 가능 금액 안내', '안녕하세요. 스타벅스 코리아입니다.\n\n스타벅스 e카드 교환권 등록 가능 금액에 대해 안내드립니다.\n\n \n\n적용일 : 2023년 1월 3일\n스타벅스 e카드 교환권 등록 가능 금액 : 계정 당 일별 최대 50만원까지 등록 가능\n일별 스타벅스 e카드 교환권 등록 한도가 초과된 경우, 익일 등록하여 이용하시기 바랍니다.\n\n앞으로도 보다 나은 서비스로 항상 보답하겠습니다. 고맙습니다.'),
(4, 'Notice_1674716872417.jpg', 'starbucks_stockholder_meeting', '제26기 정기주주총회 권리행사 기준일 공고', '당사는 상법 제354조 및 당사 정관 제13조에 따라\n매년 이사회에서 정한 날 주주명부에 기재되어 있는 주주를 권리행사 주주로 하는 바,\n제26기 정기주주총회 주주권 행사 및 배당주주 확정을 위한\n기준일은 2022년 12월 31일임을 안내해 드립니다.\n\n2022.12.16\n서울특별시 중구 퇴계로 100\n주식회사 에스씨케이컴퍼니\n대표이사 손정현'),
(5, 'Notice_1674717112349.jpg', 'starbucks_tos', '홈페이지 이용약관 / 스타벅스 카드 이용약관 개정 안내', '');

-- --------------------------------------------------------

--
-- 테이블 구조 `store_basic_info`
--

CREATE TABLE `store_basic_info` (
  `sbi_seq` int NOT NULL,
  `sbi_branch_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sbi_address_basic` varchar(250) NOT NULL,
  `sbi_address_detail` varchar(250) NOT NULL,
  `sbi_open_time` time NOT NULL DEFAULT '08:00:00',
  `sbi_close_time` time NOT NULL DEFAULT '22:00:00',
  `sbi_close_day` varchar(250) NOT NULL,
  `sbi_min_order` int NOT NULL DEFAULT '5000',
  `sbi_ceo_name` varchar(64) NOT NULL,
  `sbi_business_address` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sbi_phone` varchar(64) NOT NULL,
  `sbi_min_delivery_time` varchar(64) NOT NULL DEFAULT '15',
  `sbi_max_delivery_time` varchar(64) NOT NULL DEFAULT '100'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `store_basic_info`
--

INSERT INTO `store_basic_info` (`sbi_seq`, `sbi_branch_name`, `sbi_address_basic`, `sbi_address_detail`, `sbi_open_time`, `sbi_close_time`, `sbi_close_day`, `sbi_min_order`, `sbi_ceo_name`, `sbi_business_address`, `sbi_phone`, `sbi_min_delivery_time`, `sbi_max_delivery_time`) VALUES
(1, '대구계산점', '대구광역시 중구 ', '대구 중구 달구벌대로415길 1', '08:00:00', '22:00:00', '2023-01-31', 5000, '주봉진', '123-45-67890', '053-256-8356', '10', '30'),
(2, '대구 시티센터', '대구광역시 중구', '대구 중구 국채보상로 611 대구시티센터 104호\r\n', '08:00:00', '22:00:00', '2023-01-31', 5000, '박진혁', '321-23-12332', '053-427-8509', '5', '30'),
(3, '종로고택점', '대구광역시 중구', '중앙대로77길 22 (종로2가)', '08:00:00', '22:00:00', '2023-02-27', 5000, '이도영', '205-52-25502', '053-250-5252', '5', '30'),
(4, '대구228중앙공원점', '대구광역시 중구', '동성로2가 66-1', '08:00:00', '22:00:00', '2023-02-27', 5000, '류승지', '122-45-78910', '1522-3232', '15', '100'),
(5, 'test', '대구광역시 중구', 'test', '08:00:00', '22:00:00', '2023-02-27', 5000, 'test', '987-65-43210', '053-111-1111', '15', '100'),
(7, '대구중앙로역점', '대구광역시 중구', '남일동 국채보상로 582', '08:00:00', '22:00:00', '2023-01-01', 5000, '그린컴퓨터아트', '122-05-45834', '01012345678', '30', '60');

-- --------------------------------------------------------

--
-- 테이블 구조 `store_gps_info`
--

CREATE TABLE `store_gps_info` (
  `sgi_seq` int NOT NULL,
  `sgi_lat` double NOT NULL,
  `sgi_lon` double NOT NULL,
  `sgi_sbi_seq` int NOT NULL,
  `sgi_title` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `store_gps_info`
--

INSERT INTO `store_gps_info` (`sgi_seq`, `sgi_lat`, `sgi_lon`, `sgi_sbi_seq`, `sgi_title`) VALUES
(1, 35.8664858, 128.589709, 1, '대구계산점'),
(2, 35.8709704, 128.598091, 2, '대구 시티센터점'),
(3, 35.86842252161194, 128.5924682706477, 3, '종로 고택점'),
(4, 35.8663314, 128.591842, 4, '대구 228 중앙공원점');

-- --------------------------------------------------------

--
-- 테이블 구조 `store_image`
--

CREATE TABLE `store_image` (
  `si_seq` int NOT NULL,
  `si_img_file` text NOT NULL,
  `si_number` int NOT NULL,
  `si_uri` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `store_image`
--

INSERT INTO `store_image` (`si_seq`, `si_img_file`, `si_number`, `si_uri`) VALUES
(1, 'Store_1675234231453.jpg', 1, 'store_rodeo'),
(2, 'Store_1675234247137.jpg', 2, 'store_dongsungplaza'),
(3, 'Store_1675234256590.jpg', 3, 'store_jongrogotek'),
(4, 'Store_1675234264090.jpg', 4, 'store_228');

-- --------------------------------------------------------

--
-- 테이블 구조 `store_menu_connect`
--

CREATE TABLE `store_menu_connect` (
  `smc_seq` int NOT NULL,
  `smc_menu_stock` int NOT NULL DEFAULT '100',
  `smc_sbi_seq` int NOT NULL,
  `smc_mbi_seq` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `store_menu_connect`
--

INSERT INTO `store_menu_connect` (`smc_seq`, `smc_menu_stock`, `smc_sbi_seq`, `smc_mbi_seq`) VALUES
(1, 81, 1, 1),
(2, 82, 1, 2),
(3, 63, 1, 3),
(4, 95, 1, 4),
(5, 98, 1, 5),
(6, 98, 1, 6),
(7, 100, 1, 7),
(8, 100, 1, 8),
(9, 100, 1, 9),
(10, 100, 1, 13),
(11, 100, 1, 14),
(12, 68, 1, 15),
(13, 100, 1, 17),
(14, 6, 1, 20),
(15, 8, 1, 21),
(16, 3, 1, 22),
(17, 5, 1, 23),
(18, 3, 1, 24),
(19, 6, 1, 27),
(20, 3, 1, 29),
(21, 2, 1, 30),
(22, 6, 1, 33),
(23, 3, 1, 35),
(24, 100, 2, 1),
(25, 100, 2, 2),
(26, 100, 2, 3),
(27, 100, 2, 4),
(28, 100, 2, 5),
(29, 100, 2, 8),
(30, 100, 2, 9),
(31, 100, 2, 10),
(32, 100, 2, 11),
(33, 100, 2, 12),
(34, 100, 2, 13),
(35, 100, 2, 14),
(36, 100, 2, 16),
(37, 100, 2, 18),
(38, 100, 2, 19),
(39, 5, 2, 21),
(40, 8, 2, 23),
(41, 6, 2, 25),
(42, 4, 2, 26),
(43, 8, 2, 28),
(44, 7, 2, 31),
(45, 9, 2, 32),
(46, 5, 2, 34),
(47, 4, 2, 35),
(48, 100, 3, 1),
(49, 100, 3, 2),
(50, 100, 3, 3),
(51, 100, 3, 4),
(52, 100, 3, 5),
(53, 100, 3, 6),
(54, 100, 3, 7),
(55, 100, 3, 8),
(56, 100, 3, 9),
(57, 100, 3, 10),
(58, 100, 3, 11),
(59, 100, 3, 12),
(60, 100, 3, 13),
(61, 100, 3, 14),
(62, 100, 3, 15),
(63, 100, 3, 16),
(64, 100, 3, 17),
(65, 100, 3, 18),
(66, 100, 3, 19),
(67, 4, 3, 20),
(68, 7, 3, 21),
(69, 8, 3, 22),
(70, 5, 3, 23),
(71, 3, 3, 24),
(72, 4, 3, 25),
(73, 3, 3, 26),
(74, 6, 3, 28),
(75, 5, 3, 30),
(76, 3, 3, 31),
(77, 3, 3, 32),
(78, 3, 3, 33),
(79, 5, 3, 34),
(80, 4, 3, 35),
(83, 100, 4, 1),
(84, 100, 4, 2),
(85, 100, 4, 3),
(86, 100, 4, 4),
(87, 100, 4, 5),
(88, 100, 4, 8),
(89, 100, 4, 9),
(90, 100, 4, 10),
(91, 100, 4, 11),
(92, 100, 4, 12),
(93, 100, 4, 13),
(94, 100, 4, 14),
(95, 100, 4, 16),
(96, 100, 4, 17),
(97, 100, 4, 19),
(98, 7, 4, 21),
(99, 7, 4, 22),
(100, 5, 4, 24),
(101, 7, 4, 25),
(102, 6, 4, 28),
(103, 5, 4, 29),
(104, 4, 4, 30),
(105, 8, 4, 32),
(106, 4, 4, 32),
(107, 5, 4, 35);

-- --------------------------------------------------------

--
-- 뷰 구조 `member_grade_view`
--
DROP TABLE IF EXISTS `member_grade_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `member_grade_view`  AS SELECT `a`.`sb_mi_seq` AS `sb_mi_seq`, `a`.`cnt` AS `cnt`, (case when (`a`.`cnt` > 10) then '다이아' when (`a`.`cnt` > 5) then '골드' when (`a`.`cnt` > 3) then '실버' when (`a`.`cnt` < 3) then '브론즈' else '브론즈' end) AS `member_grade` FROM (select `shopping_basket`.`sb_mi_seq` AS `sb_mi_seq`,count((case when (`shopping_basket`.`sb_status` = 3) then 3 end)) AS `cnt` from `shopping_basket` group by `shopping_basket`.`sb_mi_seq` order by `cnt` desc) AS `a` ;

-- --------------------------------------------------------

--
-- 뷰 구조 `menu_basket_info`
--
DROP TABLE IF EXISTS `menu_basket_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `menu_basket_info`  AS SELECT `a`.`mbi_seq` AS `mbi_seq`, `a`.`mbi_name` AS `mbi_name`, `a`.`mbi_cost` AS `mbi_cost`, `a`.`mbi_status` AS `mbi_status`, `a`.`mbi_explain` AS `mbi_explain`, `a`.`mbi_pc_seq` AS `mbi_pc_seq`, `b`.`mii_seq` AS `mii_seq`, `b`.`mii_img_file` AS `mii_img_file`, `b`.`mii_number` AS `mii_number`, `b`.`mii_uri` AS `mii_uri` FROM (`menu_basic_info` `a` join `menu_image_info` `b` on((`b`.`mii_number` = `a`.`mbi_seq`))) ;

-- --------------------------------------------------------

--
-- 뷰 구조 `menu_ranking`
--
DROP TABLE IF EXISTS `menu_ranking`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `menu_ranking`  AS SELECT `a`.`mbi_name` AS `mbi_name`, `a`.`mbi_seq` AS `mbi_seq`, any_value(`a`.`mii_img_file`) AS `img_file`, any_value(`a`.`mii_uri`) AS `img_uri`, count((case when (`a`.`sb_status` = 3) then 3 end)) AS `cnt` FROM (select `a`.`sb_seq` AS `sb_seq`,`a`.`sb_status` AS `sb_status`,`a`.`sb_order_date` AS `sb_order_date`,`a`.`sb_request` AS `sb_request`,`a`.`sb_receive` AS `sb_receive`,`a`.`sb_payment` AS `sb_payment`,`a`.`sb_number` AS `sb_number`,`a`.`sb_smc_seq` AS `sb_smc_seq`,`a`.`sb_mi_seq` AS `sb_mi_seq`,`a`.`sb_order_number` AS `sb_order_number`,`a`.`sb_basket_price` AS `sb_basket_price`,`b`.`smc_seq` AS `smc_seq`,`b`.`smc_menu_stock` AS `smc_menu_stock`,`b`.`smc_sbi_seq` AS `smc_sbi_seq`,`b`.`smc_mbi_seq` AS `smc_mbi_seq`,`c`.`mbi_seq` AS `mbi_seq`,`c`.`mbi_name` AS `mbi_name`,`c`.`mbi_cost` AS `mbi_cost`,`c`.`mbi_status` AS `mbi_status`,`c`.`mbi_explain` AS `mbi_explain`,`c`.`mbi_pc_seq` AS `mbi_pc_seq`,`d`.`mii_seq` AS `mii_seq`,`d`.`mii_img_file` AS `mii_img_file`,`d`.`mii_number` AS `mii_number`,`d`.`mii_uri` AS `mii_uri` from (((`shopping_basket` `a` left join `store_menu_connect` `b` on((`a`.`sb_smc_seq` = `b`.`smc_seq`))) left join `menu_basic_info` `c` on((`c`.`mbi_seq` = `b`.`smc_mbi_seq`))) left join `menu_image_info` `d` on((`c`.`mbi_seq` = `d`.`mii_number`)))) AS `a` GROUP BY `a`.`mbi_name` ORDER BY `cnt` DESC ;

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `counpon_member_info`
--
ALTER TABLE `counpon_member_info`
  ADD PRIMARY KEY (`cmi_seq`);

--
-- 테이블의 인덱스 `coupon_info`
--
ALTER TABLE `coupon_info`
  ADD PRIMARY KEY (`ci_seq`),
  ADD UNIQUE KEY `ci_code` (`ci_code`);

--
-- 테이블의 인덱스 `event_detail_image`
--
ALTER TABLE `event_detail_image`
  ADD PRIMARY KEY (`edi_seq`);

--
-- 테이블의 인덱스 `event_image`
--
ALTER TABLE `event_image`
  ADD PRIMARY KEY (`ev_seq`);

--
-- 테이블의 인덱스 `membership_card`
--
ALTER TABLE `membership_card`
  ADD PRIMARY KEY (`card_seq`);

--
-- 테이블의 인덱스 `membership_card_image`
--
ALTER TABLE `membership_card_image`
  ADD PRIMARY KEY (`cardimage_seq`);

--
-- 테이블의 인덱스 `membership_card_qr_image`
--
ALTER TABLE `membership_card_qr_image`
  ADD PRIMARY KEY (`cardqr_seq`);

--
-- 테이블의 인덱스 `member_grade_info`
--
ALTER TABLE `member_grade_info`
  ADD PRIMARY KEY (`mgi_seq`);

--
-- 테이블의 인덱스 `member_info`
--
ALTER TABLE `member_info`
  ADD PRIMARY KEY (`mi_seq`),
  ADD UNIQUE KEY `mi_id` (`mi_id`),
  ADD UNIQUE KEY `mi_business_num` (`mi_business_num`),
  ADD KEY `member_info_FK` (`mi_sbi_seq`);

--
-- 테이블의 인덱스 `menu_basic_info`
--
ALTER TABLE `menu_basic_info`
  ADD PRIMARY KEY (`mbi_seq`),
  ADD UNIQUE KEY `mbi_name` (`mbi_name`);

--
-- 테이블의 인덱스 `menu_image_info`
--
ALTER TABLE `menu_image_info`
  ADD PRIMARY KEY (`mii_seq`);

--
-- 테이블의 인덱스 `menu_nutrition`
--
ALTER TABLE `menu_nutrition`
  ADD PRIMARY KEY (`mn_seq`);

--
-- 테이블의 인덱스 `menu_option_category`
--
ALTER TABLE `menu_option_category`
  ADD PRIMARY KEY (`moc_seq`);

--
-- 테이블의 인덱스 `menu_option_info`
--
ALTER TABLE `menu_option_info`
  ADD PRIMARY KEY (`moi_seq`);

--
-- 테이블의 인덱스 `menu_qr_image`
--
ALTER TABLE `menu_qr_image`
  ADD PRIMARY KEY (`mqi_seq`);

--
-- 테이블의 인덱스 `productcate_optioncate_connection`
--
ALTER TABLE `productcate_optioncate_connection`
  ADD PRIMARY KEY (`poc_seq`);

--
-- 테이블의 인덱스 `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`pc_seq`),
  ADD UNIQUE KEY `pc_name` (`pc_name`);

--
-- 테이블의 인덱스 `shopping_basket`
--
ALTER TABLE `shopping_basket`
  ADD PRIMARY KEY (`sb_seq`);

--
-- 테이블의 인덱스 `shopping_basket_option`
--
ALTER TABLE `shopping_basket_option`
  ADD PRIMARY KEY (`sbo_seq`);

--
-- 테이블의 인덱스 `store_announcement`
--
ALTER TABLE `store_announcement`
  ADD PRIMARY KEY (`sa_seq`);

--
-- 테이블의 인덱스 `store_basic_info`
--
ALTER TABLE `store_basic_info`
  ADD PRIMARY KEY (`sbi_seq`),
  ADD UNIQUE KEY `sbi_branch_name` (`sbi_branch_name`);

--
-- 테이블의 인덱스 `store_gps_info`
--
ALTER TABLE `store_gps_info`
  ADD PRIMARY KEY (`sgi_seq`);

--
-- 테이블의 인덱스 `store_image`
--
ALTER TABLE `store_image`
  ADD PRIMARY KEY (`si_seq`);

--
-- 테이블의 인덱스 `store_menu_connect`
--
ALTER TABLE `store_menu_connect`
  ADD PRIMARY KEY (`smc_seq`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `counpon_member_info`
--
ALTER TABLE `counpon_member_info`
  MODIFY `cmi_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 테이블의 AUTO_INCREMENT `coupon_info`
--
ALTER TABLE `coupon_info`
  MODIFY `ci_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 테이블의 AUTO_INCREMENT `event_detail_image`
--
ALTER TABLE `event_detail_image`
  MODIFY `edi_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- 테이블의 AUTO_INCREMENT `event_image`
--
ALTER TABLE `event_image`
  MODIFY `ev_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- 테이블의 AUTO_INCREMENT `membership_card`
--
ALTER TABLE `membership_card`
  MODIFY `card_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- 테이블의 AUTO_INCREMENT `membership_card_image`
--
ALTER TABLE `membership_card_image`
  MODIFY `cardimage_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 테이블의 AUTO_INCREMENT `membership_card_qr_image`
--
ALTER TABLE `membership_card_qr_image`
  MODIFY `cardqr_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- 테이블의 AUTO_INCREMENT `member_grade_info`
--
ALTER TABLE `member_grade_info`
  MODIFY `mgi_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 테이블의 AUTO_INCREMENT `member_info`
--
ALTER TABLE `member_info`
  MODIFY `mi_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- 테이블의 AUTO_INCREMENT `menu_basic_info`
--
ALTER TABLE `menu_basic_info`
  MODIFY `mbi_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- 테이블의 AUTO_INCREMENT `menu_image_info`
--
ALTER TABLE `menu_image_info`
  MODIFY `mii_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- 테이블의 AUTO_INCREMENT `menu_nutrition`
--
ALTER TABLE `menu_nutrition`
  MODIFY `mn_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- 테이블의 AUTO_INCREMENT `menu_option_category`
--
ALTER TABLE `menu_option_category`
  MODIFY `moc_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- 테이블의 AUTO_INCREMENT `menu_option_info`
--
ALTER TABLE `menu_option_info`
  MODIFY `moi_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- 테이블의 AUTO_INCREMENT `menu_qr_image`
--
ALTER TABLE `menu_qr_image`
  MODIFY `mqi_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- 테이블의 AUTO_INCREMENT `productcate_optioncate_connection`
--
ALTER TABLE `productcate_optioncate_connection`
  MODIFY `poc_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- 테이블의 AUTO_INCREMENT `product_category`
--
ALTER TABLE `product_category`
  MODIFY `pc_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 테이블의 AUTO_INCREMENT `shopping_basket`
--
ALTER TABLE `shopping_basket`
  MODIFY `sb_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=506;

--
-- 테이블의 AUTO_INCREMENT `shopping_basket_option`
--
ALTER TABLE `shopping_basket_option`
  MODIFY `sbo_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=683;

--
-- 테이블의 AUTO_INCREMENT `store_announcement`
--
ALTER TABLE `store_announcement`
  MODIFY `sa_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- 테이블의 AUTO_INCREMENT `store_basic_info`
--
ALTER TABLE `store_basic_info`
  MODIFY `sbi_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- 테이블의 AUTO_INCREMENT `store_gps_info`
--
ALTER TABLE `store_gps_info`
  MODIFY `sgi_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 테이블의 AUTO_INCREMENT `store_image`
--
ALTER TABLE `store_image`
  MODIFY `si_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 테이블의 AUTO_INCREMENT `store_menu_connect`
--
ALTER TABLE `store_menu_connect`
  MODIFY `smc_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- 덤프된 테이블의 제약사항
--

--
-- 테이블의 제약사항 `member_info`
--
ALTER TABLE `member_info`
  ADD CONSTRAINT `member_info_FK` FOREIGN KEY (`mi_sbi_seq`) REFERENCES `store_basic_info` (`sbi_seq`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
