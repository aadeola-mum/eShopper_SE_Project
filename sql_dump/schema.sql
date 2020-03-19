-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: cs425_eshopper
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `merchants`
--

DROP TABLE IF EXISTS `merchants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merchants` (
  `can_sell` bit(1) DEFAULT NULL,
  `biz_name` varchar(255) NOT NULL,
  `identity_proof_img_url` varchar(255) DEFAULT NULL,
  `office_phone_1` varchar(255) NOT NULL,
  `office_phone_2` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `office_address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKrf2w0mv2oqsl71nwy5l6i11jo` (`office_address_id`),
  CONSTRAINT `FKa759srj6ts95j9qh089b6gbei` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKrf2w0mv2oqsl71nwy5l6i11jo` FOREIGN KEY (`office_address_id`) REFERENCES `addresses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `discount` double NOT NULL,
  `order_id` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `tax` double NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4q98utpd73imf4yhttm3w0eax` (`product_id`),
  CONSTRAINT `FK4q98utpd73imf4yhttm3w0eax` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `discount` double NOT NULL,
  `order_number` varchar(255) NOT NULL,
  `paid` bit(1) NOT NULL,
  `price` double NOT NULL,
  `tax` double NOT NULL,
  `buyer_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3xgr62w5xyqceift7x0ekyc5r` (`buyer_user_id`),
  CONSTRAINT `FK3xgr62w5xyqceift7x0ekyc5r` FOREIGN KEY (`buyer_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders_order_details`
--

DROP TABLE IF EXISTS `orders_order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_order_details` (
  `orders_id` bigint(20) NOT NULL,
  `order_details_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_22wlf6q0mc7mkuqp7bvm3dm2d` (`order_details_id`),
  KEY `FKpjk9623wyjqoyvueqb09eh2ws` (`orders_id`),
  CONSTRAINT `FK5tqkl4l12ncaw7q7ua2gc4a3w` FOREIGN KEY (`order_details_id`) REFERENCES `order_details` (`id`),
  CONSTRAINT `FKpjk9623wyjqoyvueqb09eh2ws` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  `tax_in_percentage` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `discount` double NOT NULL,
  `is_available` bit(1) NOT NULL,
  `price` double NOT NULL,
  `product_number` varchar(255) DEFAULT NULL,
  `qty_avail` bigint(20) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) NOT NULL,
  `merchant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKf8n58m1em5ufo3px612bqwl8v` (`merchant_id`,`title`),
  KEY `FK1bfbbw5vei53vhmynbdfxq50n` (`category_id`),
  CONSTRAINT `FK1bfbbw5vei53vhmynbdfxq50n` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`),
  CONSTRAINT `FKt1yvv81v320ba41fq28k7had2` FOREIGN KEY (`merchant_id`) REFERENCES `merchants` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `products_images`
--

DROP TABLE IF EXISTS `products_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_images` (
  `products_id` bigint(20) NOT NULL,
  `images_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_68u3rm4tfmsixwa8nyfjg36xa` (`images_id`),
  KEY `FKbp8jstfr20o4dtau7wgu5khju` (`products_id`),
  CONSTRAINT `FKbp8jstfr20o4dtau7wgu5khju` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKdddgec4wnu9ruw05r7qdl765i` FOREIGN KEY (`images_id`) REFERENCES `product_image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q9npl2ty4pngm2cussiul2qj5` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `billing_address_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `shipping_address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FK59ttwko9dwcdjb2u2ech0h1qe` (`billing_address_id`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  KEY `FK479ibfkapj5mr9ctiasvy7yip` (`shipping_address_id`),
  CONSTRAINT `FK479ibfkapj5mr9ctiasvy7yip` FOREIGN KEY (`shipping_address_id`) REFERENCES `addresses` (`id`),
  CONSTRAINT `FK59ttwko9dwcdjb2u2ech0h1qe` FOREIGN KEY (`billing_address_id`) REFERENCES `addresses` (`id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-19 13:12:34
