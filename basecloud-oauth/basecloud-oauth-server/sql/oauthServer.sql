/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.32 : Database - oauth-server
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oauth-server` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oauth-server`;

/*Table structure for table `ClientDetails` */

DROP TABLE IF EXISTS `ClientDetails`;

CREATE TABLE `ClientDetails` (
  `appId` varchar(256) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ClientDetails` */

/*Table structure for table `oauth_access_token` */

DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(250) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_access_token` */


/*Table structure for table `oauth_approvals` */

DROP TABLE IF EXISTS `oauth_approvals`;

CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_approvals` */

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '	\r\n客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: "unity-resource,mobile-resource".\r\n\r\n可以根据上图知道，我们有Resource Server资源服务器。，资源服务器可以有多个，我们可以为每一个Resource Server（一个微服务实例）设置一个resourceid。\r\n\r\nAuthorization Server给client第三方客户端授权的时候，可以设置这个client可以访问哪一些Resource Server资源服务，如果没设置，就是对所有的Resource Server都有访问权限。',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '	用于指定客户端(client)的访问密匙; 在注册时必须填写(也可由服务端自动生成).\r\n对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appSecret,与client_secret是同一个概念.',
  `scope` varchar(256) DEFAULT NULL COMMENT '指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: "read,write".\r\n\r\n@EnableGlobalMethodSecurity(prePostEnabled = true)启用方法级权限控制\r\n\r\n然后在方法上注解标识@PreAuthorize("#oauth2.hasScope(''read'')")',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password".\r\n在实际应用中,当注册时,该字段是一般由服务器端指定的,而不是由申请者去选择的,最常用的grant_type组合有: "authorization_code,refresh_token"(针对通过浏览器访问的客户端); "password,refresh_token"(针对移动设备的客户端).\r\nimplicit与client_credentials在实际中很少使用，可以根据自己的需要，在OAuth2.0 提供的地方进行扩展自定义的授权',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '当grant_type=authorization_code时, 第一步 从 spring-oauth-server获取 ''code''时客户端发起请求时必须有redirect_uri参数, 该参数的值必须与 web_server_redirect_uri的值一致. 第二步 用 ''code'' 换取 ''access_token'' 时客户也必须传递相同的redirect_uri.\r\n在实际应用中, web_server_redirect_uri在注册时是必须填写的, 一般用来处理服务器返回的code, 验证state是否合法与通过code去换取access_token值.\r\n在spring-oauth-client项目中, 可具体参考AuthorizationCodeController.java中的authorizationCodeCallback方法. 当grant_type=implicit时通过redirect_uri的hash值来传递access_token值.',
  `authorities` varchar(256) DEFAULT NULL COMMENT ' @PreAuthorize("hasAuthority(''admin'')")可以在方法上标志 用户或者说client 需要说明样的权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时).\r\n在服务端获取的access_token JSON数据中的expires_in字段的值即为当前access_token的有效时间值.\r\n在项目中, 可具体参考DefaultTokenServices.java中属性accessTokenValiditySeconds.\r\n在实际应用中, 该值一般是由服务端处理的, 不需要客户端自定义.',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天).\r\n若客户端的grant_type不包括refresh_token,则不用关心该字段 在项目中, 可具体参考DefaultTokenServices.java中属性refreshTokenValiditySeconds.\r\n在实际应用中, 该值一般是由服务端处理的, 不需要客户端自定义.',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:\r\n{"country":"CN","country_code":"086"}\r\n按照spring-security-oauth项目中对该字段的描述\r\nAdditional information for this client, not need by the vanilla OAuth protocol but might be useful, for example,for storing descriptive information.\r\n(详见ClientDetails.java的getAdditionalInformation()方法的注释) 在实际应用中, 可以用该字段来存储关于客户端的一些其他信息,如客户端的国家,地区,注册时的IP地址等等.',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '设置用户是否自动Approval操作, 默认值为 ''false'', 可选值包括 ''true'',''false'', ''read'',''write''.\r\n该字段只适用于grant_type="authorization_code"的情况,当用户登录成功后,若该值为''true''或支持的scope值,则会跳过用户Approve的页面, 直接授权.',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_details` */

/*Table structure for table `oauth_client_token` */

DROP TABLE IF EXISTS `oauth_client_token`;

CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(25) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_token` */

/*Table structure for table `oauth_code` */

DROP TABLE IF EXISTS `oauth_code`;

CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_code` */

/*Table structure for table `oauth_refresh_token` */

DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_refresh_token` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
