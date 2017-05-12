/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.35 : Database - zb_server
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zb_server` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zb_server`;

/*Table structure for table `ACT_EVT_LOG` */

DROP TABLE IF EXISTS `ACT_EVT_LOG`;

CREATE TABLE `ACT_EVT_LOG` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_EVT_LOG` */

/*Table structure for table `ACT_GE_BYTEARRAY` */

DROP TABLE IF EXISTS `ACT_GE_BYTEARRAY`;

CREATE TABLE `ACT_GE_BYTEARRAY` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_GE_BYTEARRAY` */

insert  into `ACT_GE_BYTEARRAY`(`ID_`,`REV_`,`NAME_`,`DEPLOYMENT_ID_`,`BYTES_`,`GENERATED_`) values ('2502',1,'leave.bpmn','2501','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">\n  <process id=\"leave\" name=\"My process\" isExecutable=\"true\">\n    <userTask id=\"deptleaderaudit\" name=\"éƒ¨é—¨é¢†å¯¼å®¡æ‰¹\" activiti:candidateGroups=\"éƒ¨é—¨ç»ç†\"></userTask>\n    <exclusiveGateway id=\"exclusivegateway1\" name=\"Exclusive Gateway\"></exclusiveGateway>\n    <userTask id=\"hraudit\" name=\"äººäº‹å®¡æ‰¹\" activiti:candidateGroups=\"äººäº‹\"></userTask>\n    <sequenceFlow id=\"flow3\" name=\"åŒæ„\" sourceRef=\"exclusivegateway1\" targetRef=\"hraudit\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${deptleaderapprove==\'true\'}]]></conditionExpression>\n    </sequenceFlow>\n    <userTask id=\"modifyapply\" name=\"è°ƒæ•´ç”³è¯·\" activiti:assignee=\"${applyuserid}\"></userTask>\n    <sequenceFlow id=\"flow4\" name=\"æ‹’ç»\" sourceRef=\"exclusivegateway1\" targetRef=\"modifyapply\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${deptleaderapprove==\'false\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"flow6\" sourceRef=\"deptleaderaudit\" targetRef=\"exclusivegateway1\"></sequenceFlow>\n    <exclusiveGateway id=\"exclusivegateway2\" name=\"Exclusive Gateway\"></exclusiveGateway>\n    <sequenceFlow id=\"flow7\" sourceRef=\"modifyapply\" targetRef=\"exclusivegateway2\"></sequenceFlow>\n    <sequenceFlow id=\"flow8\" name=\"é‡æ–°ç”³è¯·\" sourceRef=\"exclusivegateway2\" targetRef=\"deptleaderaudit\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${reapply==\'true\'}]]></conditionExpression>\n    </sequenceFlow>\n    <endEvent id=\"endevent1\" name=\"End\"></endEvent>\n    <sequenceFlow id=\"flow9\" name=\"ç»“æŸæµç¨‹\" sourceRef=\"exclusivegateway2\" targetRef=\"endevent1\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${reapply==\'false\'}]]></conditionExpression>\n    </sequenceFlow>\n    <exclusiveGateway id=\"exclusivegateway3\" name=\"Exclusive Gateway\"></exclusiveGateway>\n    <sequenceFlow id=\"flow10\" sourceRef=\"hraudit\" targetRef=\"exclusivegateway3\"></sequenceFlow>\n    <sequenceFlow id=\"flow11\" name=\"æ‹’ç»\" sourceRef=\"exclusivegateway3\" targetRef=\"modifyapply\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${hrapprove==\'false\'}]]></conditionExpression>\n    </sequenceFlow>\n    <userTask id=\"reportback\" name=\"é”€å‡\" activiti:assignee=\"${applyuserid}\"></userTask>\n    <sequenceFlow id=\"flow12\" name=\"åŒæ„\" sourceRef=\"exclusivegateway3\" targetRef=\"reportback\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${hrapprove==\'true\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"flow13\" sourceRef=\"reportback\" targetRef=\"endevent1\"></sequenceFlow>\n    <startEvent id=\"startevent1\" name=\"Start\" activiti:initiator=\"${applyuserid}\"></startEvent>\n    <sequenceFlow id=\"flow14\" sourceRef=\"startevent1\" targetRef=\"deptleaderaudit\"></sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_leave\">\n    <bpmndi:BPMNPlane bpmnElement=\"leave\" id=\"BPMNPlane_leave\">\n      <bpmndi:BPMNShape bpmnElement=\"deptleaderaudit\" id=\"BPMNShape_deptleaderaudit\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"250.0\" y=\"220.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway1\" id=\"BPMNShape_exclusivegateway1\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"535.0\" y=\"227.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"hraudit\" id=\"BPMNShape_hraudit\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"620.0\" y=\"220.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"modifyapply\" id=\"BPMNShape_modifyapply\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"503.0\" y=\"310.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway2\" id=\"BPMNShape_exclusivegateway2\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"535.0\" y=\"410.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"endevent1\" id=\"BPMNShape_endevent1\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"890.0\" y=\"413.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway3\" id=\"BPMNShape_exclusivegateway3\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"770.0\" y=\"228.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"reportback\" id=\"BPMNShape_reportback\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"855.0\" y=\"221.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"startevent1\" id=\"BPMNShape_startevent1\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"140.0\" y=\"230.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"flow3\" id=\"BPMNEdge_flow3\">\n        <omgdi:waypoint x=\"575.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"620.0\" y=\"247.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"575.0\" y=\"247.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow4\" id=\"BPMNEdge_flow4\">\n        <omgdi:waypoint x=\"555.0\" y=\"267.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"555.0\" y=\"310.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"555.0\" y=\"267.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow6\" id=\"BPMNEdge_flow6\">\n        <omgdi:waypoint x=\"355.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"535.0\" y=\"247.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow7\" id=\"BPMNEdge_flow7\">\n        <omgdi:waypoint x=\"555.0\" y=\"365.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"555.0\" y=\"410.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow8\" id=\"BPMNEdge_flow8\">\n        <omgdi:waypoint x=\"535.0\" y=\"430.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"302.0\" y=\"429.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"302.0\" y=\"275.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"48.0\" x=\"361.0\" y=\"438.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow9\" id=\"BPMNEdge_flow9\">\n        <omgdi:waypoint x=\"575.0\" y=\"430.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"890.0\" y=\"430.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"48.0\" x=\"659.0\" y=\"437.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow10\" id=\"BPMNEdge_flow10\">\n        <omgdi:waypoint x=\"725.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"770.0\" y=\"248.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow11\" id=\"BPMNEdge_flow11\">\n        <omgdi:waypoint x=\"790.0\" y=\"268.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"789.0\" y=\"337.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"608.0\" y=\"337.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"672.0\" y=\"319.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow12\" id=\"BPMNEdge_flow12\">\n        <omgdi:waypoint x=\"810.0\" y=\"248.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"855.0\" y=\"248.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"810.0\" y=\"248.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow13\" id=\"BPMNEdge_flow13\">\n        <omgdi:waypoint x=\"907.0\" y=\"276.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"907.0\" y=\"413.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow14\" id=\"BPMNEdge_flow14\">\n        <omgdi:waypoint x=\"175.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"250.0\" y=\"247.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),('2503',1,'leave.leave.png','2501','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0Ê\0\0Ì\0\0\0\r©Q\0\0\0:IDATxÚíÝœ\\e\'üEñ¯Œ0Ë°Ž³®Ë²¼.¯Â‹.‹f]FAA‘±Ó©\\ 1Ò@`0KCÌÂ KÒÝ	xAP@ÌÈu•®*H&CVa¸Ê\0†Kb  µÏÿt¦RéNº“îêêîï÷óy>uºêT7œÊù×ó{žsii\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0`»U*•~øákÖ¬ù]±X¬Ü|óÍZ´ÞÞÞÊÊ•+ÚLÿJu\\\0\Z(:W«V­ª¬[·®òòË/kMÔÖ¯__¹õÖ[ŸM­Ïù—\n¨ãê8µ4ƒZ@ƒD±Ö¹jêNÖË©ˆßï_* Ž«ãÔÒj\r#š\nds·ÔÁzÅ¿T@WÇ1¨¥Ô\Z$9Q›¾ƒUñ/PÇÕqjiµ€&ë`ýö7OV¾ýâÊ}½gf-–ã9EUPÇ5uƒZöU`ÒíçŸY[¹÷G+ÿtÃI›´x.^SXmCïPÇÕquƒZš}hxÑ~ü¾ë6ë\\åíW÷]¯°*Ú0VJ­R}TÇÕquÜ –A-Í¾\n4®hß_þ›A;XñšÂªhÃ80µgR[R}<DWÇÕqƒZµ4û*Ð°¢}ïÍƒíxMaU´aŒBò!5ðõ“5,«ãê¸A-ƒZš}•	¢³³óË—/ÿê¢E‹îX¸páú¹sçnœ1cFeÚ´iY›3gÎ«_þò—Ÿ9óÌ3ï:ÿüó¿ñ•¯|åßÙj:XŠ60@HÞÚóê¸:®ŽÔ²¯jöUšÛâÅ‹?qúé§ÿãÌ™3Ÿråšk®©Ü}÷ÝÙ×_}õÕJnÃ†\r•x òÃþ°rî¹çVbýœOï™3eÊ”lÉÆí¸Ä`E;^SXmhƒ¶†ó×\'Õáê¸:nPKPÖì«ŒSííívê©§Þõ…/|á÷+V¬È‚ðplÜ¸±²zõêÊW¾ò•ßÏ;÷©£>úÓ¶jãŠöƒ«—\rZ´ã5…UÑ††ä­…àI×	WÇÕqƒZµ4û*ãÐÂ…çuÔQ¯|÷»ß­¼ðÂ•íuûí·WRPÞxÌ1Ç\\ÓÚÚú[xô‹ö†µwWîýñé›l¦çâ5…UÑ†Qvà0;ÕÃ]_WkÕqƒZµ4}.\Zç”SNùÆ±ÇûêOúÓÊHzî¹ç*Ë—/ß8{öì\'RXþ[zt‹v´Gî¸d³‚Ï)ªŠ64($²\rï{~2„eu\\7¨ePK³¯2ÎBò¼yó~ç–Ë/¿üw3gÎ|6…å÷Úâ£ØÁzé¥ÊƒÿpÁæ#›é¹xMaU´¡ÉBòH½_×ÔqƒZµì«0râpë˜IÍœ»öÚk_Jay­™åÑ)Úqsû_¬îô0 x-ÖQ\\ma[;Ç±Ñ¿G×ÔqƒZµì«°íâÂ]qNò]wÝUi”ï}ï{OLŸ>ý:WÄÁ¢Šñ“–*÷üð¯í\\å-Ö‰upEF8$ÔÓ„î„«ãê¸A-ƒZš}•qà”SN¹\'.ÜÕhóçÏ¢­­mžO`û‹öÖ\nµ®hÃ(ª?gñR;²ú8õï›°çBªãê¸A-ƒZš}•&÷IŽ[@ÄÕ­‡kíÚµ¯NŸ>ý_\n…ÂÎ>‰í+ÚC)Ô[*à\n­¢\r#ÔIŽûíÔ*ÕÇ¡†åÁÞ7!Ã²:®ŽÔ2¨¥ÙWir§Ÿ~ú?^uÕU•±röÙgß—‚òY>‰í+ÚÛÚ¹Ê›B«hÃ„ä–jç¹RÓ†–kCrÞŽ¨y}ÂÞ©Ž«ãµjiöUšXggç;fÎœùû\r6ŒYP^»víº”í\\åí+Úš¢\r\r6Xx(ôn©>Ðú°þ„:¼SWÇ\rjÔÒì«4±.¸à¬…VÆÚ—¾ô¥Ÿ§°ü_}\":XŠ64Nª»7ocíÝÚ9ŽC\rËC\rÉõýƒÆû6UÇÕqƒZú\\š}•&¶hÑ¢;®¿þú1ÊK—.]íðk,E\ZkÚ´i•hQ{ÚÚÚ>4Ä·\r5¬n­>Ü\\û÷×7k\'|¨ÛTWÇ\rjésiú\\4±…®¿ûî»Ç<(¯Y³æ®Ô±(ë´ê`)ÚÐøšSÓnÚJÇq¸3EƒuÂ_·!¹¾óÈxÝ¦ê¸:nPKŸKÓç¢‰Í;wãºuëÆ<(?õÔSO¦bõ¤N«–¢\rcZsú;ŽÔžm=œr NöÏ·#$oïOSlSu\\7¨¥Ï¥ésÑÄfÌ˜QyõÕW+Í Š”N«–¢\rc_sj;ŽÕ™–ííìÔ	ßž<Ü³¦Û¦ê¸:nPKŸKÓç¢É;IÍBPÖÁšHmŸ¥¦‹vøá‡çä%ÛYâ^7@§ûçÕç·Ç’íùÿ+Ì˜9&ÛU×ù6¨¥Ï¥	Ê˜QrPÖ†ÞF²ÀÄà7¿ùÍþŸãvaƒ­ûÔSOeëÿä\'?Ùìµ^xa³ç}ôÑlýxßwÜQ¹öÚk+?øÁ*Ÿýìg³åxíÊ+¯ì_ÿ#ùHåŸøDåÁŒ{lg-–ëÿûÿèêêÚê:\'žxbÿr\\Ì._èÿIÑf’ÎÝž:ˆ‡ÔÜ¶okÚžÎ÷pîßºÅÎw{Oiÿö®â¬Žîòõ­½§|zû’•»Öÿ‚ÎÎï¿1½¾(–ç/-í×¨mÚ,ïøæÎ;äõ£~¿ï}ïÛä¹Ÿýìgã¢.OôOƒZµeAÍrŽòúõëŸpŽrã;X«W¯®‹ÅÊ9çœ“}ÞtÓMYhÝsÏ=³PšÔžžžlý_|1{|ÃÞPÙwß}+=ôPj?õ©Oõ‡Þ#Ž8b“¿A÷­o}kåè.Ö}×»Þ5àÓ.»ìRùØÇ>6`Gê²Ë.Ûä¹[o½5û]ñ7ãq·Ýv«ì·ß~ÙrÈßóž÷lö{.¼ðÂþåË/¿|³eE›I\\sêr}(}¾etçÜ–°¼Ùáœ’ëÃðqÝ7¾­óâò›Ú»KËjŸÏÂsWù´Ž®ò‘ÝÅ§×¿ÏÍ]^~K#¶i3åC=tÐ×£ÖGËëì®»îšµ¼Î~ýë_Ï~Ç¯~õ«¦¯ËtFÙ Ö$Ô\Zêþ|ÞyçmòÜŠ+*{ì±G¶üÄOlòZL`pÀ•¿ø‹¿Èú€±¯ßyç•éÓ§g?ï¾ûî‚2“×i§öëûî»¯Y®z½J§µ±¬èØÄhýóÏ?Ÿµ(°~ã1:4ë×¯ßdD?f\râç×yh®mo~ó›³àœÿüÌ3Ïdëç;ŠuÝ™3gfÏåÏßu×]ýï‰0¼ÓN;U,Xu´òÎV¬?^«ýB¨íDí¸ãŽY0ÏŽ@ê©§fËñÿ•ÿž´=³üwæ¿+®\0_\ZŠ6“°æÜV($ ÚÙÝÆN÷…-_ h8ð/0ÁxÁò[wŽv´ŽîÒG,ë}ßüîòÉ±Î±Ëzß¹ «ôÉ¬SžBr®»‹íó—•‰çæw•¾¿c´·i3å¾[Z\'ÂïûßÿþÍ‚òòåË³º_ÿûšµ.O° lPk’\rj\ruŽ}&úmþð‡³©Ø¯¢ÿÏÅëõëßvÛmÙã#<Ò¿ÿæGæ}7A™I)î£|ã76Å}”SúšNkã;XQgÏžÖXÎsb£€æëÆHd”ú]õA9ÖÎüãìÐëèÅs £ã•¯sõÕW÷¿\'f\ZößÿÍ~wóüïÇïŠçž}öÙlä3žÏ_‹Y‰XŽì:õ3¼ã‹¤¥:£ñ³¢Í$©9[êl¹Ó;Ì¼¥[Î¥¾OË ·œÉÃp{wyF>;5¿§ÜVÁêÞtÝÒñ›Ìbeïé= QÛ´:ßQWc»¿ýíoßâzÑQþÀ>ÕÖÌÑb96ëgœš¹.O lPk’jm­E+ïSE_*ß¿c@+Ârý)ùþ–O‚äýÀÚ>YýÏ‚2“JÚyÎ]¼xñ˜åyóæÝ›\nÕÖÆu°b´0±‰sÓ\"Gp‡ÑE\'%ïàÔž¯Ëñ\\ß­å˜)ˆó“óB{É%—l2ÚÑ™ª/Â÷Þ{oö\\„å8(þ{â°½xŒV„úú\"¿;øÑ¡‹å8Ç­~¦#_?þ_ó/”|  –÷Þ{oE¶£ó;ŒÜ²a9Ÿ);l £óÝÙÙùºö®Ò©Y^Úû®ÊçwKìB¾Þ	ËŠïNÏ•wÐ³NzWiNÇòò{µ›¡óÏ0Emè(¡¼Å¡™q”OÔÖ¨“Ñb9jø@§ÍZ—ÇyP6¨5‰µ†:ñø†nÈNŸ‹SÙb€)úSÑb?l©™QÎ¡b\0+£Ÿûçã?ž—íáÐk&­“N:éOŽ<òÈßÇE˜ÆðÊë\n…ÂoS\0z“O¤±¬øÜó0-:\'ùdÛ<,çë\'(G‹Ãxâ°ê­Œ‚]-dÙ!ßƒ^Vü¿ð…/dï«ï”å¡:/èõWÕxàg>ó™þõV­Z•sëäÛ¯¥æ¢–º‹Š)Ú°Íð¡†äá†åüï\r’cö©/ü–÷Ní…Ñ‘ŽÀç2¶÷”«íX§@}xGOiî¦ïÒ·ÇcßÞŽõÁœÕÇX,,GÇ{ s”ãüã¸Ñ±uy’ÖqƒZ`_ÝZ‹~V>ÑûZ>Hõï|g³ÉŽüç8—¹¥z”_Kõ4»h_üâ³ý­¸f Ì¤vòÉ\'ÿó-·Ü2fAyÙ²ew´µµ}Ë\'Ñø¢Ç·ùÈb^hk¯:šwZ†”ëï«=\'¹¥æpŸñŒ¢‡öåúš7oÞ&ç\'Gg.þ;öÚk¯Í:dµªX?)­=œ0òÿþÚÅÔ¾7‚û–fUm°^?ctdËðo)3P\'üˆatö[âÍhq‘ 8œ3ž‹p\0ŠÙ«ÎÎrÿ¬Qt²cVê¸îÞ=çwçeòžò“©ó×åÚ£}âÂŽõw-ˆ™¥¨¿å…VÚÛÛûO³iöº<‰ë¸A­	”c\"!ïÇÅ>½hÑ¢lùøãï¿^~\r€X?ö«ØÿjûbÑ‹ }±è“•ËåMNw”™”N9å”Â‰\'žø»±¸MTÚI_™>}ú¿Ì˜1ãO}/Úùˆc×úÃ¢c$1/ºy0nPŽé‹.º(;÷,By^Ì¢WÂŽâ;ÐmEâoÇßÉg)Z¶p{‘x­ö¡|æbçwÎ:p[;.¾âêß-Õ™ó|°@Ñ†a…åÚs!k;ÓÃ¹ïê`ïÒ¹–qîb\\¨ö¹/¿j§ìâ@=¥oô¯—:äñÜüîÒâ<4GHŽç:ºŠ—Öê‰ÜùŽí³Fõ§½äÁy(3ÊQgãVã¥.Oò:nPkåÚ>]í@SÜr3úrÑâè½–.æ•ïû±ßÅ©q(v~¯Ëf”™ôæÎûTÕh‹-º{Ú´i=>±)ÚqÑ•(„qþYÞ¡É¯z³¿Ñq‰ÒÖ.æ™<T×ß3?ÌúŒ3ÎÈ~Žð›w~â÷·rŸÌ|v#ŸõŽsg†ÒñËg.ê[\\Á;ïpÅ—H>›’i´Ôœ3ßÏSÑ†!«Ÿ±úƒjçy¸WÇ­ßou“ð‚úçâÐÎŽîÒù¹œ£sÞ÷|ñìÚ‡tnÏ…ÆSç;ê\\ò<Ð=’ãµ˜QÊ7kg ã1f~óŽx\\‹\"¾+ÆK]VÇ\rjMä œï3ù)o±\\Û‡«?ôº>(GŸ+¿}Tü\Zî£Éœ9s>~ôÑGoŒ/ÆFI_~\n…nmm}‹O`lƒr~ãÚ™åÎË/øP¾Ú[GÕßë8Ÿ!ŽÑÉx¬¿êbüÍú{jÖþ÷{î¹ý¥¡|Aä‡ŒÔâµ–šÅÔvÞâµ¸xXb˜ßTÑ†í\nË#Ñ©_?‚¿oBÖñáž·gÊï:0Ø­ òAÓúSuòÁÍÓO?}X÷f©Ëê¸A­É”[ª‡K×÷åòA©n\'ÕR3?Ÿ|òÉƒö÷e&¥cŽ9æï–.]úJ#ÁN;ÞqÈõÔ©S´å\'vÑnô9wŠ6Lˆ°<Ò¡[W—ÕqƒZ~_É‡˜d¨ŸôÈÃpý©µA9³Ž‹íåGæ3Íµ÷A·¯2iÅåñ?ÿùÏ?rÉ%—lÍ¼nÝºWgÏžýD¡Ph·Õu°&jS´™Ä†<³4JïWÇ5uÜ –}uˆw?±¯Â¥ðºó¬Y³6\\uÕU/ÖLr5$Ÿek+Ú:X0áÃòp;ÏC:ÇQ×ÔqƒZöUMŸ‹†‹+PÏœ9síE]ôÔHŸ“‡[›IV´m˜†;Ó4ág¦ÔquÜ –}U³¯2ÎÅÌr\nµ×ÅÕ°}ôÑ—¶÷Pquë¸p—s’mE„åì“Úó-ƒÜwU×ÔqƒZöUÍ¾JSikk›—óÓ_ýêWïyè¡‡†5ÃüÔSO­[¾|ùí1‹<mÚ´\\ÝZÑV´aR:¨\Z‚ÚÂëë\'SHVÇÕqƒZöUÍ¾Ê7ÝÎÔŸ3gÎÏÏ9çœÛÊåò=öØ£µÁxÃ†\rO¯Y³æ®¥K—®ž7oÞ½…Bá·)h+å¶mE&µÁ:á“jfJWÇ\rjÙW5û*T\n¾ª†æ›R{$µWR«TÛ“©Ý–Ú×R›2{öì7ÙbŠ¶¢\rŠ\'mHVÇÕqƒZöUÍ¾\n(ÚŠ6PßÙ^2™C²:®ŽÔ²¯jöU@ÑV´ZÅQH-ÕÃ;;;;_·-¿¤³óûoTÇ5uÜ –}UÓçmE&„jPÎ´wÛ;–ôþyÇyÅÝæ/í}WàhÝåMî’0YùŽ®r®ç./¿¥½»´L×ÔñÆ\rjésiöU@ÑÖmhTPî)}ã¸î•»¤à{xác—õ¾3oùló—Î_µÓüžòQ¤#TÏï.ŸëÏï.?¿»8O×Ôñ‘×ÚÚúú´¯¾ªÏe_µ¯Š¶¦hCƒ‚ò	=å=\"·w¿³Åó»JçÕ†Ý¾då®ñØÙYÞ!Bq,wt—ÎH¹-æ}cÝx¾óâò›ÔqMy|ðŽi_}IŸË¾j_mMÑ†åþ@Ü]:¿}Iyï‚Ï‰Ã­£µ÷”vt•æÄë1cœÍ§ ÖùhrÝÑ]üxÿú]ÅKÕqMy³fÍzsÚW«Ïe_µ¯ãF±XT›»½˜Šö+þ¥ÂàA9¡Ž@ÜÞU>-žK¡wQxî*ÎÊg”û^ë=`Áò[wÏ‚s\nÌ}ë”O;®ûÆ·Åºê¸¦Ž¼ÖÖÖ·§}õ7‚² ,(ãÆÊ•+Ÿ\\·nâØ¤míÚµ›Šöýþ¥ÂàA9f‰ãÐé–÷Ã¨cf¸F¹»|]Hî)áxAWé“q®r®SˆŽÙåü0lu\\SÇG%(ÿQÚW7˜œ0¨eP7J¥Ògn¹å–ß<ýôÓÿª@6W±ŽÎUooï£©}Î¿T<(Çc„ÞÉÇw•>ØÞS>¬?//¿·ù¼ânñ³ÇqÑ¯ŽîâÙµ¿kÁ²Þ÷©ãš:>òfÍšõÇi_}Úä„A-ƒZÀ¸’¾ÀI…áöE‹CNÆk»âŠ+*ÑiÏÿ5->‹û…dZPŽóŒã1f†ãâ^ùëqÅëìù¸ÊuOñóó{Š­Çu÷îyÂ²â»cÝ|½ìõºÛH©ãš:>bûé¿Im­É	ƒZµ\0Ææ‹øúè4O™2e[&‡Baz%f†;zJs³ÀœÂpNÝßzÊ‡ÅÕ¬ãPëÚÙå¾C®‹gg·ŠJ-ÎSNaùý¶(ŒÊ÷óî©=fKL¼A­	2AaP˜¸ÚÚÚ>”Šõ+Q°…ÂA¶Lš¸¯@“kmm}OÚW¶%Ô`\0\Z”¯Žb]m·Û\" “4‡éÓ§ÿû´¯þÂ–Pƒhl‘Þ\'ŸMÎ[\nÎ‡Ø2 “Œ½ôüÓ¾ú3[B\r ±EúúÚœÏ*;WtÒ€¦Ê{¥}õ[B\r qz³Ùä¼\n…Ãl!ÐIÆÖôéÓ÷Naùm	5€Æè›\nÉÕv›-:iÀ˜ï§û¸~ˆ\Z@c¿x_ÙBPv®2è¤c¿Ÿî—Ú\Z[B\r 1Åùú-…dWÀ4`ìM:uÿ´¯þ½-¡ `öy )\nMûê-¶„\Z€‚\rØç¾ýô¿§ÖkK¨Á\0(Ø€}hÉnõñ´¯þÈ–PƒP°û<Ð’Ýê“),ß`K¨Á\0(Ø€}èÛO?Úµ¶„\Z€‚\rØç¾ýô³©]eK¨Á\0(Ø€}hÉÎQn-\nß·%Ô`\0lÀ>´d·‡*¤}õr[B\r@Áìó@ß~:+µKm	5\0°Ï}ûéìÔ.¶%Ô`\0lÀ>´d‡^¡­­í[¶„\Z€‚\rØç¾ýô˜Ô.°%Ô`\0lÀ>ôí§Ç¦¶Ì–PƒP°û<Ð’Ýj~¡PXjK¨Á\0(Ø€}hÉÎQ>!…åsm	5\0ùýûúØÇ·Òn·¥ é‚ò—SPþ[B¿\0ùý{ŸÔ^ÙRPNñCl)hº}÷¯S[lKèw `£³ß´… |Û”)Sv°• ¹´µµýB¡p†-¡ß€‚\rŒÎ>¾Ï`A9uÄ³… )÷Û3S[hKèw `£·Ÿt®òíf“¡i÷ÙÅqøµ-¡ß€‚\rŒÞ~¾Ù¹ÊÎM†æòŠzÙú]\0(ØÀèv¼¯6›ãC\nÉÿ+í³l	ý.\0l`tƒò‡jÎM>È¦ÊKÓ>;ß–Ðï@ÁFÏÎU6›M¿¯.KíX[B¿\0ýý}û<4¿¶¶¶¯\n…/Úú]\0(ØÐ”*•Ê?üðŠ5kÖü®X,Vn¾ùf­	ZoooeåÊ•¿Nm¦¥LÀïæS;Ê–Ðï@Á†¦!yÕªU•uëÖU^~ùe­‰Úúõë+·Þzë³)4Î¿T&ØwóÅ©Í¶%ô»\0P°¡)ÅL²ÜÔaùå›o¾ù~ÿR™`ßÍ—\nGKèw `CsŠÃ­Òæn)(¿â_*I\nÉW´µµM³%ô»\0P°¡)Åù°ÂhÓeuŒ‰”¿Ÿ‚r«-¡ß€‚\rã:(ÿö7OV¾ýâÊ}½gf-–ã9AVP†møn¾*µÏÚú]\0(Ø0nƒòóÏ¬­Üû£…•ºá¤MZ<¯	³‚2G[[Ûu…BáP[B¿\0ÆmP~ü¾ë6ÉyûÕ}×³‚2÷»ùÆ–¶%ô»\0P°aÜåûË3hPŽ×„YA†ùÝü£”?nKèw `Ã¸\rÊ÷ÞÜ9hPŽ×„YA¹Q:;;_·mïûþm½¦ún.\n…Ùú]\0(Ø (k‚òvjï.¶w,éýóŽóŠ»Í_Úû®ÀÑ:ºËÖ®7YùŽ®òA±<wyù-íÝ¥e¶^óH!ùÖ©S§~Ä–Ðï@Á†q”ã*×ƒåxM˜””{Jß8®{å.)øAøØe½ïÌ[>Ûü¥óWí4¿§|TéÕó»Ë\'Çúó»KÇÏï.Î³›â»ùïSPÞß–Ðï@Á†q”\\½lÐ ¯	³‚òh;¡§¼Gãöîâ·b¶x~Wé¼ÚÃ°Û—¬Ü5;;Ë;D(ŽåŽîÒ)0·¥À¼o¬Ïw^\\~“½¾)¾›×¤¶Ÿ-¡ß€‚\rã6(oX{wåÞŸ¾ùa×é¹xM˜”%…ÝóÛ—”÷N!øœ8Ü:Z{OiaGWiN¼3ÆÙÌq\nÒiÆ!×ÝÅ÷¯ßU¼ÔVlŠïæ;¦OŸþA[B¿\0ÆmPŽöÈ—l”ã9AVPn„8„:q{Wù´ìçîò¢þðÜUœ•Ï(÷½Ö{À‚å·îžç˜ûÖ)Ÿv\\÷o‹uíõMñÝ|W[[Û¶%ô»\0P°aüå—^ª<øl~Øuz.^fåÑ³Äqèt\nËûÇaÔ13Ü?£Ü]¾®?$÷‰p¼ «ôÉ8W9×)DÇìr~¶½¾)¾›ïIAy/[B¿\0ÆeP~þ™µ•_¬îôåx-ÖhåÑ¡7Bòñ]¥¶÷”ëÇËËïí_>¯¸[<Æìq\\ô«£»xvíïX°¬÷}¶dS|7ß_(ö°%ô»\0P°a|å—^ª<ù`©rÏÿzÐœ·X\'Ö5»,(¦8Ï8cf8.î•?W¼Îž«\\÷??¿§Øz\\wïž\',+¾;ÖÍ×Ë^¯»cöÝü`kkë{m	ý.\0l7Ayk³Èf—åFê»OrñìŽžÒÜ,0§0‡S÷·žòaq5ë8Ôºvv¹ïëâÙÙ­¢R‹ó”SX~¿½¾)¾›NAù=¶„~\0\n6Œ› <”Yä-Í.·‚2lå»ù±Ôv·%ô»\0P°aÜåm\rÉyneØÊwó3gÎÜÕ–Ðï@Á†q”5AFù»yÝ¬Y³þØ–Ðï@ÁAY• œêÜ>mmmWû×Ì8únÞÐÚÚúG¶„~\0\n6ÊÚˆåÈ©]ŸÚ+jãì»ùÙ3f¼Í–Ðï@ÁAY‘ \\óæ_3ãè»ù…C=ôm	ý.\0l”µí\nÊmmmŠC¬ë² Ì8ün~éàƒÞÑ–Ðï@ÁAYÛ¦vÅWTšA”ÇßÍ¯¶¶¶¾Þ–Ðï@ÁAYÛ¦¶¥p¬iãµ©¾ú]\0(Ø (×µ\r6Ä¾Yyæ™g*/¾øbåÎ;ïÌž«måry³÷Å{Î9çœÊSO=ÕÿóâÅ‹·ú÷®¾úêÊ®»î:èë?ûÙÏ*wÝu×€ï²Ë.kŠC¯§Nz`ªi·	€~\0\n6LÀ íõ¯}å€È‚rÒËñxî¹çöé|Ý·¾õ­Y˜çn½õÖÊõ×__¹÷Þ{³Ÿò“ŸTnºé¦lyýúõýïyðÁû—¯½öÚÊ»ßýîþŸW­Z5`(ŽßuÃ\r7TŽ8âˆì=ñÜE]TùÁ~-ç},/æU(Jµm• èw `ÃÊÑž}öÙþzÉ%—dûï¿û×‹`ÜÕÕ•=÷ï|\'{œ>}zåòË/ïÿ•W^¹YðýÀ>Þ…VvÚi§Êi§Ö€ëgŠó`Á}¯½öÚ$(·ŒáÌò`·‡ªÎ0¯”ý.\0lçA9f}W¯^…Ï˜©ÍCm>c3Å<ðÀ&A9»µ:fcf8ÿùùçŸß,(çá¹~F¹öµø»y(Žåø=yËÿ^¾|Çw4MPÎUg˜oó¯Ðï@Á†q\Z”kÃjœ;œ/÷ôôTþò/ÿ2;ìùÇ?þñ&AùÑG­\\xá…Ùsûî»oÿûb8žûÔ§>5àïßÒŒr~tÌ çëÇyÊqèu„èb±ØßbV9üñ¦Ê\0ú]\0(Ø0Aƒr´<ÄÖÏ(ï¼óÎÙ¹ÉñÜ¯~õ«ìüæØ¿ãÜäx}îÜ¹Ùkf‡;£\\ûÜŠ+ú—÷Ûo¿MÎUÎÿ[e\0ý.\0åQÊq˜õ#<Òþq~¯üB]±ÞO<‘=FŽähqÈöG>ò‘l9‡ŽàüÂ/lÓŒríúwß}wå¡‡Ê–>øàM‚ò@\0”ô»\0l”G4(¿á\roÈ.Î•ÏÇ,o<Æ¡Ö-53Êµï©=§8<ï¼ó6[7~ç`W½ŽYé~÷Ž;îØÖ§L™\"(èw(Ø (76(Ç¬nKõ¶Nù}Œó‹r\r”cÖ·>øÖÎ<ÔZ ßWÒÎ—óÕ–-(èw(Ø (Z‹s‘ã0ëÚçâPê–êE»joaw—]vÉãÂ^qæyóæe?ó›ßÌÞ“ßnª¾EèÝsÏ=·øßòÌ3Ïl6sï:ãŒ3²ŸãlA@¿@ÁAYã&(ú]\0(Ø (k‚2 ß€‚\r‚²&(ú]\0(Ø (k‚2 ß€‚\r‚²&(ú]\0(Ø (k‚2 ß€‚\r‚²&(ú]\0(Ø (k‚2 ß€‚\r‚²&(ú]ú]\0\n6Êš  ß `ƒ ¬	Ê\0ú]\0\n6Êš  ß `ƒ ¬	Ê\0ú]\0\n6Êš  ß `ÃäU,…Ñæn/¦ üŠ©€~\0\n64ÈÊ•+Ÿ\\·n@Ú¤míÚµ›‚òýþ¥ú]\0(ØÐ ¥Ré3·ÜrËož~úéL›k&9Brooï£©}Î¿T@¿\0\Z(±Cn¾ùæÛãß8v<·ØçÇûÿCµÅgq¿èw `öy\05\0°Ï¨Á\0(Ø€}@\r@Áìó\0j0\0\n6`ŸPƒP°û<€\Z€‚\rØçÔ`\0lÀ>€\Z `öy\0Ô`\0°Ï (Ø€}\05@Áìó\0¨Á\0\n6`Ÿ@\rP°û<\0j0€‚\rØçPƒlÀ>€\Z `+Ø`Ÿ@\r@Áû<\0j0\0\n6`ŸPƒP°û<€\Z€‚\rØçÔ`\0lÀ>  `öy\05\0°Ï¨Á\0(Ø€}@\r@Áìó\0j0\0\n6`ŸPƒP°û<€\Z€‚\rØçÔ`\0lÀ>  `öy\05X\rP°û<\0j0€‚\rØçPƒlÀ>€\Z `Í¾_ûøVÚí¶€~\0\n6L–ý{ŸÔ^ÙRPnkk;Ä–Ðï@Á†É´ß´… |Û”)Sv°•\0ô»\0P°a2íãû”…Âa¶À¨Õ_§¿\0ÊÀ8ë¬Ýn6`Tk¯Ó_\0e`<uÖtÎ\0\ZRþ (Í*ã«Í&4¼Ÿåô\0Ahâ ü¡šÎÙA¶@ÃúZN”fï¬éœ4´ö:ý@Pš¹³vøá‡Ûç\ZÌé/\0‚2Ð¼âëJõ€Æe§¿\0Ê@:0µgR[R}tØ@cû\\N”&Éy8ŽÙŒõÂ2ÀÈ+\n;·µµ¥.Ö´¯¥VNíñºy=·†Jë|+­û…´üg¶\Z€ ŒmHÞÚó\0lƒ©S§X=ùÅ-Ü;y úº-µÙf›e`ô´•0œ¿î|9€mïKÅU­of8¬Ý³Ì¶*€ ŒnHÞZ6³°R¨=«þÖO3fÌ¨œuÖY•+VTî»ï¾Ê“O>YyñÅ+aãÆ•uëÖU~ñ‹_T®ºêªl½Y³f\r˜oJ¿çOma\0A9¶o¦x¸ëLj­­­o©žÜngÏž]¹ì²Ë² </¼ðBåšk®©´··WêîµüLzœbkÊÀÈ…äC¶á}ÏË\0C\nÉ«jCíÂ…³™ãíñê«¯Vn¼ñÆlFºæw?ŸÚçlu\0Ah|H©÷L†¾Ó&3Éqˆu„Ü‘²víÚÊI\'T–_lkkS—e`líÂ]þ=\0Nõœäþ»fÍšÊhˆsšãüåÚ™åô·÷ð	\0ÊÀðÃíH6mf`ó>Ó>µîŠ™äÑþZ¼xñ&÷_Ž{4û$\0e`è¡6ÉÚ‘ÕÇá¨Ÿ|ÔhkkûÇÚs’GòpëÁ<÷Üs•£>ºö_K|\0‚20´|HMØývj•êãPÃò`ï–’©S§X{uëí½p×püô§?­¿¸×ŸùD\0e`h!9Y\r»•a„åÚœ·#j^wÎ2 ¿4mÚõyX[@5Ú¹çž[–¿æ”Í\r^\n½[\nË­á\0ë;g˜´â¼à¸òtô™âÖMÃ½OòHxè¡‡6¹¿òìÙ³ßá“”ÍCòAÃ¿…å¡†äú°ì0l`\"…à›Sû¯[Z\'ÓiyH+Q•öööþ°œþ›óéÊÀðÂêÖÂòpCríß_ßbf˜@ý jð¼9â\r²Î×\Zu¥ë-¹òÊ+]Ô@P	ÉC\r©ƒ…å×mcHÎ9g˜pA¹¦ÝT?Ãœž+ç¯?ðÀc”ãžÍµÁÞ§ (ƒ¼mát °üóíÉÛûßÐìA¹?ˆæ9ýüxþ|#¯v]ï±ÇÛäžÊ>=€Æ|Aü>µWS{%µ©½œÚKÕ‹Wükj/¤öÛêm	žMí7q1‰ôø/©mHí×©­Om]jO§öTjO¤¶6µ_U¿dKíÑÔ~™Ú#©=œÚC©=˜Ú/RûyúRz =ÞŸÚÏÒò}éñÞÔîIëîôøOÕ{Þ™ÚOS»#µÛSûIjÿ\'µ5©Ý–Ú?¤ö÷©­Jmeú=·¦Ç[bD8-—Òc1µÞøL?J¿ó‡1‚œÚiù†ôø¿ãê–iùºôxmj×¤vUj?HÏý]z\\‘Þûýôxej›–¯H—§ö½Ô.Mí’ôÜwÓãwR»8-_”¿Ú…éýßJßL_O¤v~Z^ž—¥Ö“–»ÓcWzÏÒ8¬*=þ¯ôxnö•–ÏIË“–ÏNËg¥Çÿ™Úâ´üÕô¸(=ž‘;S;=­÷?ÒãÂÔ¾’–OK^?5-Ÿ’¿œOJÏ˜–OHËÒrGZnOËóÓòñiù¸ôxlzœ›žûRZ>&-1=ÎIíè´ü…ôxTj•Úìôó‘i½#Òò¬´<3-ÏHËÓÓr¡znWzhkMŸKOýeZ><-6Î±JËŸIËŸNË‡¦åC¦OŸþÉôxpúù ´ü‰´üñ´üÕ[sü÷´ü±´üßÒãGÓãGÒsDg&-ïŸ–ÿKZþp>—–÷KïÿÿÓã>éñƒé¹¤åÿ/-ï–ÿsZïý3fÌøÓò^iù?µ¶¶î™÷Hÿ!=÷¾´Þ¿OËïMËÿ.-ÿyZ~OÜ’#-¿;=îžÞû§é¹ÝÒò¿™9sæ®iùOÒûß5kÖ¬?NË»ÄÅ_Òã¥õwŠ‹®¤å·§÷¼íÓŸþô[Óò[Òzo>ôÐCÿ0-ÿ?|ðŽéñsæÌyÃ”)SvHË¯ïìì|]5Ànïî@ay{Br}XvÎ20á‚rísíÏ/¾øâ˜å7nòßæÓhŒ?ˆÎytÒ£³öè¼§þ›¢#úèØG?:ûÑéÎ„€GqÄ;«Á`—\n\"<Dˆˆ0¡\"ÂEZþ·Õ ñg<ªä½J\"œDH‰°’–ÿc5¸ü§2h\"ØDÈ‰°¡\'ÂO„ ´¼o5í)‚R„¦O¢\"LE¨JËSªëc¸\"xE‹0¡,ÂY„´ôÚ§\"°Ep‹\0W\rrŸp!/Â^„¾´<µ\Z\0§E ¬Ã™#4FxŒ™–?_\r”GUæÑÕÐyL„Ð£JÓò¼j@=>k5¸.ˆ0¡6Âm„Ü´|r5ðž\Z¸\Z„¿R\rÇ§GX®†æ3«:‚ôâÖ°#lGèŽð!<ÂxZ>¯\ZÌ—FP¯öeÕ~„új¸ÿF5èGà¿°:\0pq\nT.‰Á‚´|Yuààòê@ÂßVVT~ƒiùêê@ÄµÕ‰ë«ƒ7Fç¤:ˆñ£Ô~\\ØèŽê€Ç-ÕA•ÕA‘Y](¹­:pòªƒ)wTWî¬¶ÜUx‰˜{b@¦:0su°æçÕÁ›Äùçê€ÎÃÕž_V}¯­­\n=Y zº:`´¾:ˆô/ÕA¥ßÄ SúýÏUœ~[€ú×ê ÔËÕAªWªƒV¿«`Egh{ÏE‹Ð]?“üóêóÛcÉ:™š¦i¦5A\0 ÏÖ®r½ÕA°–Ág”‡rŸåÁ˜QÆ½-„âÛcÐ<&Ì(\04§¥Ï·ŒÎ9ÊÛ–£LÔ ÜkÖqŽ2\0@‡åáÌàv¨®z=œ°ìª×ÀDÊ·Åu3jrÍ:®z\r\00ÂòÖBêÖî“¼µû,fŸ÷Q&VPÞly€uzšá>Êñ·k‚}O\0à5[›ÑÝZHnÙÆ°œþ}˜\0˜LªêÌêYg5fAù¤“Nª\rÊŸóÉ\0\0lZ\nËC\rÉÃ\rËùß’I§zçŽ¸]f%-Wž{î¹±>?ùÅ¸ÓˆO\0`s1³\\ô‘-Ã¿Oò@aùˆº¿ãœä&ÐÙùý7nm/¿j§¡ý®ò¶(]íyÊW]uUÃƒò\\P”/ö‰\0ÐÀNhçëFâ÷ÌùÆõ8’_ØJX®½ÀWmèJHnÙÊûÜjÍï.ß±¤÷Ïûî)·µ/)ï½¥\ZÖÑU¼4ÿù¸îß6wyù-}Ë+wéè.}4­}ÉÊ]Û»Ê§Åríß8aYñÝ]å#Û»Š\'Î_Ú»oGOin¶n´îòŒËoÝÝ\'ÃdT{øõÜ¹sz›¨µk×f3ÙùßŸ>}úÞ>\0FEû²Þwt—œ¿¬|HtþúZéü¼SYd£3™Ï¾DgqþÒÒ~ó»‹çßUÞkÀÎmOùªž¯v@ªë/Žß:¤ï\Z© Î¤Sv„Ü#Z†Ë§ú÷mïý›ÙQ‹:zŠÇôÕ¡òQ‹úêEªI=¥ýcæ8jFªïÏƒì‚®Ò\'ã=ùÏŒOè)ïÑ_÷ºŠKó:”êÝ²ôó¬¨ƒí=åÃŽ]ÖûÎüo¥÷“×¿ü¿!«W]¥ó|2LVÕû)ß“‡Õk®¹¦aAùÌ3ÏìÉmmmWû4\0Ý½ä-›¡é)Ÿžÿ³(5·­£»øñ¬ãÙ]úvãÔAýFý,pÇòò{Û»‹…èpÆú±Nÿrêˆnú·Ë‹bF&:ªYµ»|]Ø{ÊGuœWÜÍ§Ã…åí5ÐaÝ4PÔŸ<œFŽÙá¨U £åƒwQ7újUÔœÒQÃ²šÒ]>¹óâò›bËª£EËfŠS½ëû;ÅÖþ¿™þN_]*ŸÕ¤Ô=¥…}¿;=ßU:Õ\'ÃdV;«3¼¿üå/G=$ßxãµ‡\\¿’‚ò^>	\0FEt,ë}_6C“:˜Ñ9Ì–«ùzyÇt~OéÓ1³¾µÊ\\ÌÆä‡4ÆŒN´¬›:™ùröwkÎ	Œ¿-f«ãïFÇÕ\'C“„å‘Ý;$[#èÆÌnÄEÍŠp¡¹¯•ö{mÝÒ§ó:¯çG¦Äìs¾N6‹Ü]<;Ðé½1³u+êN{w±=f¨ûjÔ÷ß‡a§ßó½¬¶-ëýpíQ6ñ~Ÿ“]\n«×çÁuÁ‚•^xaÔBòêÕ«79ä:µc|\0ŒªŽ®Òße³È]ÅKûf“³Ù’ýáwIyïêŒð‰1+<¿»8/:”1ËØhùEsb9f‰ûÎù+z\\wïžÕåùáŒÙ¬súùø®Òãpï¾¿WœÕÑSü|ßrù´æ>¶Óö.ípëf©Q©.Å`Z¶œÂk>“œÍ&WgŠ«øøüè—ü•¾Yáâ·6ù}éwDð³Ðy@®=o9«}©Å,r>ˆ—×²ìPîºSG`2jmmý“X×çáuñâÅ•7ŽJHž={¶xÐ8ÙìJõ5q(tvÞq\n¯µ³¿¯u\ZK§ÆMçþåšÃ³û:´}çóeëw—–\r´\\ýùüxŒ€Á;ïÐÆùÎ®@Ë(„åáÎ»pW“è;w¸xvÓ¸W„Öþ\0ÜSúFÔ¬þúÓS<&«Kñ|wé»QÇ¢žÔ_ô+ôúêUñì¾ócð¯<£vÆ8ùâðíìÔê¾ªGÞy®£\0}ÚÚÚ>”Ú3yˆsˆ7lØ0¢‡[×Í$ß”ú[ly\0F·Ú]:<ªqˆa_X--Ži¾NvXtv¯âÅÑñŒ™—8;ëpÖ]Ð&‚îkÏéÞYø®9ô±¶£úÚa•}ßè˜úTaÃ=|ÚáÖMäµš]¬ë]nûëWOiaíº}ß*/ÊfŒc8µ½{¾özvñ¯î¸ŽBß@_éÔ¨gQ×¢võäÁé÷D@î)~>Þ“]d0.v3Ìé=Ù‘0®£\0ý\n…Âaù½•£Åìïš5k¶ûêÖ§Ÿ~zm@’hŒì)Õiå‡\'æ‡A/èêýoýÕîÒGó}e‡\\÷×7+nýT{Ø,T×¼/:¤ùrf½Iç·«xivNtêÈÆ¬ökáúµN0ŒAXÞ\'µçS;Ì&{ùm›úf•{ÈÎIî.-~m`íµz‘]°§47f…ûôÊ‡åµ&ÖËoå8Ždéè*Í‰à¿;«…Ùlty‡<ÌO%©?&êUœ«çIû„à5S§N=0ÙçkƒíYgUyà†{ì±ì>Éu³ÈÙáÖB2\0\r“>˜ÏªäÏç·QÉî)}ºïÖP¥Sû:ªÅy}¡øµ‹uÕËÂow±0ÐkÙ9ÌÝ¥ÿýÝ®âÒþð\\½b­O…QrP5´…××ÉÍTŸÊ;ÔÎÜÆ@\\œ+Ü¤«5&êMí­ŸâÞÇ›Ü\nªz®üµ¸Úuÿ5ÒÏÙc~­„š¿—ß^*?%Bt6ûœjb\\kÁ\'›*\n{¤@ûx]À­wÜq•+®¸¢²jÕªìêØùyÌñÁ8fŸW¬XQ9ùä“ëÃqvukî a²ÙÛ¸ˆM~htê|FÇ2ÿ9fcâ1:“¦³sõRÇ0¿J_\'µ<c°‹nEGrKW¯ŽÙèjGôóñ×¸¨Xí!ß0\n›Yv¸5ÀˆYß˜ÏªŸ]Þ–÷Iv(\0€±	ËB2À›1cÆŸ¦°»¨öB_Clq®óÅÓ§OßÛV\0›°¼DH=³gÏ~Sõüå¯ÅÅ¸84ûÉB¡pszìI¡zZZÞÙV\0;qNr¥Å- \0\0\0 ß;l\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0&‚ÿ–èíK3Á÷e\0\0\0\0IEND®B`‚',1);

/*Table structure for table `ACT_GE_PROPERTY` */

DROP TABLE IF EXISTS `ACT_GE_PROPERTY`;

CREATE TABLE `ACT_GE_PROPERTY` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_GE_PROPERTY` */

insert  into `ACT_GE_PROPERTY`(`NAME_`,`VALUE_`,`REV_`) values ('next.dbid','12501',6),('schema.history','create(5.18.0.0)',1),('schema.version','5.18.0.0',1);

/*Table structure for table `ACT_HI_ACTINST` */

DROP TABLE IF EXISTS `ACT_HI_ACTINST`;

CREATE TABLE `ACT_HI_ACTINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_HI_ACTINST` */

insert  into `ACT_HI_ACTINST`(`ID_`,`PROC_DEF_ID_`,`PROC_INST_ID_`,`EXECUTION_ID_`,`ACT_ID_`,`TASK_ID_`,`CALL_PROC_INST_ID_`,`ACT_NAME_`,`ACT_TYPE_`,`ASSIGNEE_`,`START_TIME_`,`END_TIME_`,`DURATION_`,`TENANT_ID_`) values ('10002','leave:1:2504','7507','7507','exclusivegateway2',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 15:21:29.153','2017-04-01 15:21:29.158',5,''),('10003','leave:1:2504','7507','7507','deptleaderaudit','10004',NULL,'éƒ¨é—¨é¢†å¯¼å®¡æ‰¹','userTask','3','2017-04-01 15:21:29.159','2017-04-01 15:22:36.235',67076,''),('10006','leave:1:2504','7507','7507','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 15:22:36.235','2017-04-01 15:22:36.235',0,''),('10007','leave:1:2504','7507','7507','modifyapply','10008',NULL,'è°ƒæ•´ç”³è¯·','userTask','1','2017-04-01 15:22:36.235','2017-04-01 15:24:13.572',97337,''),('10009','leave:1:2504','7507','7507','exclusivegateway2',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 15:24:13.572','2017-04-01 15:24:13.574',2,''),('10010','leave:1:2504','7507','7507','endevent1',NULL,NULL,'End','endEvent',NULL,'2017-04-01 15:24:13.575','2017-04-01 15:24:13.576',1,''),('2508','leave:1:2504','2505','2505','startevent1',NULL,NULL,'Start','startEvent',NULL,'2017-03-31 18:08:57.430','2017-03-31 18:08:57.445',15,''),('2510','leave:1:2504','2505','2505','deptleaderaudit','2511',NULL,'éƒ¨é—¨é¢†å¯¼å®¡æ‰¹','userTask','3','2017-03-31 18:08:57.446','2017-04-01 13:48:41.381',70783935,''),('2516','leave:1:2504','2513','2513','startevent1',NULL,NULL,'Start','startEvent',NULL,'2017-03-31 18:09:45.942','2017-03-31 18:09:45.944',2,''),('2518','leave:1:2504','2513','2513','deptleaderaudit','2519',NULL,'éƒ¨é—¨é¢†å¯¼å®¡æ‰¹','userTask','3','2017-03-31 18:09:45.945','2017-04-01 14:53:17.312',74611367,''),('5003','leave:1:2504','2505','2505','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 13:48:41.382','2017-04-01 13:48:41.387',5,''),('5004','leave:1:2504','2505','2505','hraudit','5005',NULL,'äººäº‹å®¡æ‰¹','userTask',NULL,'2017-04-01 13:48:41.387',NULL,NULL,''),('7503','leave:1:2504','2513','2513','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 14:53:17.313','2017-04-01 14:53:17.318',5,''),('7504','leave:1:2504','2513','2513','hraudit','7505',NULL,'äººäº‹å®¡æ‰¹','userTask',NULL,'2017-04-01 14:53:17.318',NULL,NULL,''),('7510','leave:1:2504','7507','7507','startevent1',NULL,NULL,'Start','startEvent',NULL,'2017-04-01 14:55:53.935','2017-04-01 14:55:53.937',2,''),('7512','leave:1:2504','7507','7507','deptleaderaudit','7513',NULL,'éƒ¨é—¨é¢†å¯¼å®¡æ‰¹','userTask','3','2017-04-01 14:55:53.937','2017-04-01 15:00:15.994',262057,''),('7517','leave:1:2504','7507','7507','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 15:00:16.009','2017-04-01 15:00:16.009',0,''),('7518','leave:1:2504','7507','7507','modifyapply','7519',NULL,'è°ƒæ•´ç”³è¯·','userTask','1','2017-04-01 15:00:16.009','2017-04-01 15:21:29.152',1273143,'');

/*Table structure for table `ACT_HI_ATTACHMENT` */

DROP TABLE IF EXISTS `ACT_HI_ATTACHMENT`;

CREATE TABLE `ACT_HI_ATTACHMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_HI_ATTACHMENT` */

/*Table structure for table `ACT_HI_COMMENT` */

DROP TABLE IF EXISTS `ACT_HI_COMMENT`;

CREATE TABLE `ACT_HI_COMMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_HI_COMMENT` */

/*Table structure for table `ACT_HI_DETAIL` */

DROP TABLE IF EXISTS `ACT_HI_DETAIL`;

CREATE TABLE `ACT_HI_DETAIL` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_HI_DETAIL` */

/*Table structure for table `ACT_HI_IDENTITYLINK` */

DROP TABLE IF EXISTS `ACT_HI_IDENTITYLINK`;

CREATE TABLE `ACT_HI_IDENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_HI_IDENTITYLINK` */

insert  into `ACT_HI_IDENTITYLINK`(`ID_`,`GROUP_ID_`,`TYPE_`,`USER_ID_`,`TASK_ID_`,`PROC_INST_ID_`) values ('10005','éƒ¨é—¨ç»ç†','candidate',NULL,'10004',NULL),('2507',NULL,'starter','1',NULL,'2505'),('2512','éƒ¨é—¨ç»ç†','candidate',NULL,'2511',NULL),('2515',NULL,'starter','1',NULL,'2513'),('2520','éƒ¨é—¨ç»ç†','candidate',NULL,'2519',NULL),('5001',NULL,'participant','3',NULL,'2505'),('5006','äººäº‹','candidate',NULL,'5005',NULL),('7501',NULL,'participant','3',NULL,'2513'),('7506','äººäº‹','candidate',NULL,'7505',NULL),('7509',NULL,'starter','1',NULL,'7507'),('7514','éƒ¨é—¨ç»ç†','candidate',NULL,'7513',NULL),('7515',NULL,'participant','3',NULL,'7507');

/*Table structure for table `ACT_HI_PROCINST` */

DROP TABLE IF EXISTS `ACT_HI_PROCINST`;

CREATE TABLE `ACT_HI_PROCINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_HI_PROCINST` */

insert  into `ACT_HI_PROCINST`(`ID_`,`PROC_INST_ID_`,`BUSINESS_KEY_`,`PROC_DEF_ID_`,`START_TIME_`,`END_TIME_`,`DURATION_`,`START_USER_ID_`,`START_ACT_ID_`,`END_ACT_ID_`,`SUPER_PROCESS_INSTANCE_ID_`,`DELETE_REASON_`,`TENANT_ID_`,`NAME_`) values ('2505','2505','9','leave:1:2504','2017-03-31 18:08:57.430',NULL,NULL,'1','startevent1',NULL,NULL,NULL,'',NULL),('2513','2513','10','leave:1:2504','2017-03-31 18:09:45.942',NULL,NULL,'1','startevent1',NULL,NULL,NULL,'',NULL),('7507','7507','11','leave:1:2504','2017-04-01 14:55:53.935','2017-04-01 15:24:13.608',1699673,'1','startevent1','endevent1',NULL,NULL,'',NULL);

/*Table structure for table `ACT_HI_TASKINST` */

DROP TABLE IF EXISTS `ACT_HI_TASKINST`;

CREATE TABLE `ACT_HI_TASKINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_HI_TASKINST` */

insert  into `ACT_HI_TASKINST`(`ID_`,`PROC_DEF_ID_`,`TASK_DEF_KEY_`,`PROC_INST_ID_`,`EXECUTION_ID_`,`NAME_`,`PARENT_TASK_ID_`,`DESCRIPTION_`,`OWNER_`,`ASSIGNEE_`,`START_TIME_`,`CLAIM_TIME_`,`END_TIME_`,`DURATION_`,`DELETE_REASON_`,`PRIORITY_`,`DUE_DATE_`,`FORM_KEY_`,`CATEGORY_`,`TENANT_ID_`) values ('10004','leave:1:2504','deptleaderaudit','7507','7507','éƒ¨é—¨é¢†å¯¼å®¡æ‰¹',NULL,NULL,NULL,'3','2017-04-01 15:21:29.160','2017-04-01 15:22:35.734','2017-04-01 15:22:36.173',67013,'completed',50,NULL,NULL,NULL,''),('10008','leave:1:2504','modifyapply','7507','7507','è°ƒæ•´ç”³è¯·',NULL,NULL,NULL,'1','2017-04-01 15:22:36.235',NULL,'2017-04-01 15:24:13.508',97273,'completed',50,NULL,NULL,NULL,''),('2511','leave:1:2504','deptleaderaudit','2505','2505','éƒ¨é—¨é¢†å¯¼å®¡æ‰¹',NULL,NULL,NULL,'3','2017-03-31 18:08:57.447','2017-04-01 13:48:40.781','2017-04-01 13:48:41.303',70783856,'completed',50,NULL,NULL,NULL,''),('2519','leave:1:2504','deptleaderaudit','2513','2513','éƒ¨é—¨é¢†å¯¼å®¡æ‰¹',NULL,NULL,NULL,'3','2017-03-31 18:09:45.946','2017-04-01 14:53:16.700','2017-04-01 14:53:17.234',74611288,'completed',50,NULL,NULL,NULL,''),('5005','leave:1:2504','hraudit','2505','2505','äººäº‹å®¡æ‰¹',NULL,NULL,NULL,NULL,'2017-04-01 13:48:41.388',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('7505','leave:1:2504','hraudit','2513','2513','äººäº‹å®¡æ‰¹',NULL,NULL,NULL,NULL,'2017-04-01 14:53:17.319',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('7513','leave:1:2504','deptleaderaudit','7507','7507','éƒ¨é—¨é¢†å¯¼å®¡æ‰¹',NULL,NULL,NULL,'3','2017-04-01 14:55:53.938','2017-04-01 15:00:15.462','2017-04-01 15:00:15.947',262009,'completed',50,NULL,NULL,NULL,''),('7519','leave:1:2504','modifyapply','7507','7507','è°ƒæ•´ç”³è¯·',NULL,NULL,NULL,'1','2017-04-01 15:00:16.009',NULL,'2017-04-01 15:21:29.054',1273045,'completed',50,NULL,NULL,NULL,'');

/*Table structure for table `ACT_HI_VARINST` */

DROP TABLE IF EXISTS `ACT_HI_VARINST`;

CREATE TABLE `ACT_HI_VARINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_HI_VARINST` */

insert  into `ACT_HI_VARINST`(`ID_`,`PROC_INST_ID_`,`EXECUTION_ID_`,`TASK_ID_`,`NAME_`,`VAR_TYPE_`,`REV_`,`BYTEARRAY_ID_`,`DOUBLE_`,`LONG_`,`TEXT_`,`TEXT2_`,`CREATE_TIME_`,`LAST_UPDATED_TIME_`) values ('10001','7507','7507',NULL,'reapply','string',1,NULL,NULL,NULL,'false',NULL,'2017-04-01 15:21:28.663','2017-04-01 15:24:13.706'),('2506','2505','2505',NULL,'${applyuserid}','string',0,NULL,NULL,NULL,'1',NULL,'2017-03-31 18:08:57.399','2017-03-31 18:08:57.399'),('2509','2505','2505',NULL,'applyuserid','long',0,NULL,NULL,1,'1',NULL,'2017-03-31 18:08:57.430','2017-03-31 18:08:57.430'),('2514','2513','2513',NULL,'${applyuserid}','string',0,NULL,NULL,NULL,'1',NULL,'2017-03-31 18:09:45.910','2017-03-31 18:09:45.910'),('2517','2513','2513',NULL,'applyuserid','long',0,NULL,NULL,1,'1',NULL,'2017-03-31 18:09:45.942','2017-03-31 18:09:45.942'),('5002','2505','2505',NULL,'deptleaderapprove','string',0,NULL,NULL,NULL,'true',NULL,'2017-04-01 13:48:41.174','2017-04-01 13:48:41.174'),('7502','2513','2513',NULL,'deptleaderapprove','string',0,NULL,NULL,NULL,'true',NULL,'2017-04-01 14:53:17.103','2017-04-01 14:53:17.103'),('7508','7507','7507',NULL,'${applyuserid}','string',1,NULL,NULL,NULL,'1',NULL,'2017-04-01 14:55:53.907','2017-04-01 15:24:13.705'),('7511','7507','7507',NULL,'applyuserid','long',1,NULL,NULL,1,'1',NULL,'2017-04-01 14:55:53.936','2017-04-01 15:24:13.704'),('7516','7507','7507',NULL,'deptleaderapprove','string',2,NULL,NULL,NULL,'false',NULL,'2017-04-01 15:00:15.822','2017-04-01 15:24:13.706');

/*Table structure for table `ACT_ID_GROUP` */

DROP TABLE IF EXISTS `ACT_ID_GROUP`;

CREATE TABLE `ACT_ID_GROUP` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_ID_GROUP` */

/*Table structure for table `ACT_ID_INFO` */

DROP TABLE IF EXISTS `ACT_ID_INFO`;

CREATE TABLE `ACT_ID_INFO` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_ID_INFO` */

/*Table structure for table `ACT_ID_MEMBERSHIP` */

DROP TABLE IF EXISTS `ACT_ID_MEMBERSHIP`;

CREATE TABLE `ACT_ID_MEMBERSHIP` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `ACT_ID_GROUP` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `ACT_ID_USER` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_ID_MEMBERSHIP` */

/*Table structure for table `ACT_ID_USER` */

DROP TABLE IF EXISTS `ACT_ID_USER`;

CREATE TABLE `ACT_ID_USER` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_ID_USER` */

/*Table structure for table `ACT_RE_DEPLOYMENT` */

DROP TABLE IF EXISTS `ACT_RE_DEPLOYMENT`;

CREATE TABLE `ACT_RE_DEPLOYMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_RE_DEPLOYMENT` */

insert  into `ACT_RE_DEPLOYMENT`(`ID_`,`NAME_`,`CATEGORY_`,`TENANT_ID_`,`DEPLOY_TIME_`) values ('2501',NULL,NULL,'','2017-03-31 18:07:54.693');

/*Table structure for table `ACT_RE_MODEL` */

DROP TABLE IF EXISTS `ACT_RE_MODEL`;

CREATE TABLE `ACT_RE_MODEL` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_RE_MODEL` */

/*Table structure for table `ACT_RE_PROCDEF` */

DROP TABLE IF EXISTS `ACT_RE_PROCDEF`;

CREATE TABLE `ACT_RE_PROCDEF` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_RE_PROCDEF` */

insert  into `ACT_RE_PROCDEF`(`ID_`,`REV_`,`CATEGORY_`,`NAME_`,`KEY_`,`VERSION_`,`DEPLOYMENT_ID_`,`RESOURCE_NAME_`,`DGRM_RESOURCE_NAME_`,`DESCRIPTION_`,`HAS_START_FORM_KEY_`,`HAS_GRAPHICAL_NOTATION_`,`SUSPENSION_STATE_`,`TENANT_ID_`) values ('leave:1:2504',1,'http://www.activiti.org/test','My process','leave',1,'2501','leave.bpmn','leave.leave.png',NULL,0,1,1,'');

/*Table structure for table `ACT_RU_EVENT_SUBSCR` */

DROP TABLE IF EXISTS `ACT_RU_EVENT_SUBSCR`;

CREATE TABLE `ACT_RU_EVENT_SUBSCR` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_RU_EVENT_SUBSCR` */

/*Table structure for table `ACT_RU_EXECUTION` */

DROP TABLE IF EXISTS `ACT_RU_EXECUTION`;

CREATE TABLE `ACT_RU_EXECUTION` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_RU_EXECUTION` */

insert  into `ACT_RU_EXECUTION`(`ID_`,`REV_`,`PROC_INST_ID_`,`BUSINESS_KEY_`,`PARENT_ID_`,`PROC_DEF_ID_`,`SUPER_EXEC_`,`ACT_ID_`,`IS_ACTIVE_`,`IS_CONCURRENT_`,`IS_SCOPE_`,`IS_EVENT_SCOPE_`,`SUSPENSION_STATE_`,`CACHED_ENT_STATE_`,`TENANT_ID_`,`NAME_`,`LOCK_TIME_`) values ('2505',2,'2505','9',NULL,'leave:1:2504',NULL,'hraudit',1,0,1,0,1,2,'',NULL,NULL),('2513',2,'2513','10',NULL,'leave:1:2504',NULL,'hraudit',1,0,1,0,1,2,'',NULL,NULL);

/*Table structure for table `ACT_RU_IDENTITYLINK` */

DROP TABLE IF EXISTS `ACT_RU_IDENTITYLINK`;

CREATE TABLE `ACT_RU_IDENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `ACT_RU_TASK` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_RU_IDENTITYLINK` */

insert  into `ACT_RU_IDENTITYLINK`(`ID_`,`REV_`,`GROUP_ID_`,`TYPE_`,`USER_ID_`,`TASK_ID_`,`PROC_INST_ID_`,`PROC_DEF_ID_`) values ('2507',1,NULL,'starter','1',NULL,'2505',NULL),('2515',1,NULL,'starter','1',NULL,'2513',NULL),('5001',1,NULL,'participant','3',NULL,'2505',NULL),('5006',1,'äººäº‹','candidate',NULL,'5005',NULL,NULL),('7501',1,NULL,'participant','3',NULL,'2513',NULL),('7506',1,'äººäº‹','candidate',NULL,'7505',NULL,NULL);

/*Table structure for table `ACT_RU_JOB` */

DROP TABLE IF EXISTS `ACT_RU_JOB`;

CREATE TABLE `ACT_RU_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_RU_JOB` */

/*Table structure for table `ACT_RU_TASK` */

DROP TABLE IF EXISTS `ACT_RU_TASK`;

CREATE TABLE `ACT_RU_TASK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_RU_TASK` */

insert  into `ACT_RU_TASK`(`ID_`,`REV_`,`EXECUTION_ID_`,`PROC_INST_ID_`,`PROC_DEF_ID_`,`NAME_`,`PARENT_TASK_ID_`,`DESCRIPTION_`,`TASK_DEF_KEY_`,`OWNER_`,`ASSIGNEE_`,`DELEGATION_`,`PRIORITY_`,`CREATE_TIME_`,`DUE_DATE_`,`CATEGORY_`,`SUSPENSION_STATE_`,`TENANT_ID_`,`FORM_KEY_`) values ('5005',1,'2505','2505','leave:1:2504','äººäº‹å®¡æ‰¹',NULL,NULL,'hraudit',NULL,NULL,NULL,50,'2017-04-01 13:48:41.388',NULL,NULL,1,'',NULL),('7505',1,'2513','2513','leave:1:2504','äººäº‹å®¡æ‰¹',NULL,NULL,'hraudit',NULL,NULL,NULL,50,'2017-04-01 14:53:17.319',NULL,NULL,1,'',NULL);

/*Table structure for table `ACT_RU_VARIABLE` */

DROP TABLE IF EXISTS `ACT_RU_VARIABLE`;

CREATE TABLE `ACT_RU_VARIABLE` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ACT_RU_VARIABLE` */

insert  into `ACT_RU_VARIABLE`(`ID_`,`REV_`,`TYPE_`,`NAME_`,`EXECUTION_ID_`,`PROC_INST_ID_`,`TASK_ID_`,`BYTEARRAY_ID_`,`DOUBLE_`,`LONG_`,`TEXT_`,`TEXT2_`) values ('2506',1,'string','${applyuserid}','2505','2505',NULL,NULL,NULL,NULL,'1',NULL),('2509',1,'long','applyuserid','2505','2505',NULL,NULL,NULL,1,'1',NULL),('2514',1,'string','${applyuserid}','2513','2513',NULL,NULL,NULL,NULL,'1',NULL),('2517',1,'long','applyuserid','2513','2513',NULL,NULL,NULL,1,'1',NULL),('5002',1,'string','deptleaderapprove','2505','2505',NULL,NULL,NULL,NULL,'true',NULL),('7502',1,'string','deptleaderapprove','2513','2513',NULL,NULL,NULL,NULL,'true',NULL);

/*Table structure for table `basic_config` */

DROP TABLE IF EXISTS `basic_config`;

CREATE TABLE `basic_config` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(50) NOT NULL COMMENT 'æ ‡è¯†é¡¹ç›®æ˜¯å¦å¤„äºŽç®¡ç†å‘˜è°ƒè¯•ã€ä¿®æ”¹çŠ¶æ€ï¼Œç”¨æˆ·ç™»å½•ä¹‹åŽä¼šæœ‰ç›¸åº”çš„å¼¹çª—æé†’',
  `config_value` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='å…¨å±€æ€§é…ç½®';

/*Data for the table `basic_config` */

insert  into `basic_config`(`id`,`config_name`,`config_value`) values (1,'project_debugging_tip','1');

/*Table structure for table `basic_source_download` */

DROP TABLE IF EXISTS `basic_source_download`;

CREATE TABLE `basic_source_download` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT 'èµ„æºåç§°',
  `down_url` varchar(150) NOT NULL COMMENT 'ä¸‹è½½åœ°å€',
  `icon_name` varchar(50) NOT NULL DEFAULT 'icon-download-alt' COMMENT 'å›¾æ ‡',
  `sort_num` int(11) NOT NULL DEFAULT '0' COMMENT 'æŽ’åº',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `basic_source_download` */

insert  into `basic_source_download`(`id`,`name`,`down_url`,`icon_name`,`sort_num`,`create_time`,`update_time`) values (1,'ActiveMQæ¶ˆæ¯ç›‘å¬å¤„ç†å…¥é—¨Demo(Springç®€å•é›†æˆ).zip','http://pan.baidu.com/s/1hrH9DnY','icon-download-alt',0,'2016-11-03 15:17:37','2016-11-03 15:17:39'),(2,'apache tomcaté›†ç¾¤çŽ¯å¢ƒæ­å»ºå›¾æ–‡æ•™ç¨‹.zip','http://pan.baidu.com/s/1o7DtCsq','icon-download-alt',0,'2016-11-03 15:18:34','2016-11-03 15:18:36'),(3,'chatpush-åœ¨çº¿èŠå¤©å®¤(å¯é€‰æŒ‡å®šç”¨æˆ·èŠå¤©).zip','http://pan.baidu.com/s/1eR521IM','icon-download-alt',0,'2016-11-03 15:19:33','2016-11-03 15:19:35'),(4,'crmåŽå°ç³»ç»Ÿæºç (bootstrap).zip','http://pan.baidu.com/s/1i4TcT29','icon-download-alt',0,'2016-11-03 15:19:56','2016-11-03 15:19:57'),(5,'DateTimePicker_JQueryæ—¥æœŸå’Œæ—¶é—´æ’ä»¶.zip','http://pan.baidu.com/s/1o8eyaF4','icon-download-alt',0,'2016-11-03 15:20:20','2016-11-03 15:20:22'),(6,'dwrcomet-æŽ¨é€æ¶ˆæ¯ç»™æŒ‡å®šç”¨æˆ·Demo.zip','http://pan.baidu.com/s/1kVAslEn','icon-download-alt',0,'2016-11-03 15:20:42','2016-11-03 15:20:44'),(7,'DwrPush-æ¨¡æ‹Ÿæ¶ˆæ¯æŽ¨é€è‡³æ‰€æœ‰å½“å‰åœ¨çº¿çš„ç”¨æˆ·.zip','http://pan.baidu.com/s/1i5BclKh','icon-download-alt',0,'2016-11-03 15:21:03','2016-11-03 15:21:05'),(8,'dwzå¯Œå®¢æˆ·ç«¯ springmvc ibatisç®€å•Demo.zip','http://pan.baidu.com/s/1boRTofX','icon-download-alt',0,'2016-11-03 15:21:24','2016-11-03 15:21:26'),(9,'easyuiå®Œæ•´Demo-å„ç§åŠŸèƒ½_å¸ƒå±€.zip','http://pan.baidu.com/s/1nvJb8G5','icon-download-alt',0,'2016-11-03 15:21:52','2016-11-03 15:21:54'),(10,'erpé¡¹ç›®æºç --bootstrap é€‰é¡¹å¡åˆ‡æ¢.zip','http://pan.baidu.com/s/1boLhDLl','icon-download-alt',0,'2016-11-03 15:22:13','2016-11-03 15:22:15'),(11,'Highchartsç®€å•å®žä¾‹Demo.zip','http://pan.baidu.com/s/1dFt91nF','icon-download-alt',0,'2016-11-03 15:22:37','2016-11-03 15:22:38'),(12,'HTML5 CSS3é¼ æ ‡æ‚¬åœå›¾ç‰‡ç‰¹æ•ˆ.zip','http://pan.baidu.com/s/1eSLXIee','icon-download-alt',0,'2016-11-03 15:23:01','2016-11-03 15:23:03'),(13,'Java webé¡¹ç›®ï¼šmybatisã€springmvcã€seajsã€freemarkerã€åˆ†å¸ƒå¼sessionã€è‡ªå®šä¹‰taglibç­‰.zip','http://pan.baidu.com/s/1qYzsFxU','icon-download-alt',0,'2016-11-03 15:23:21','2016-11-03 15:23:23'),(14,'JavaåŽå°ç³»ç»Ÿ-ä¸åŒæ¡†æž¶ä¼˜ç‚¹é›†æˆ-bootstrapå¸ƒå±€-æƒé™è§’è‰²ç®¡ç†(å«sql)----1.0.0ç‰ˆæœ¬.zip','http://pan.baidu.com/s/1i4GlFql','icon-download-alt',0,'2016-11-03 15:23:43','2016-11-03 15:23:44'),(15,'javaçº¿ç¨‹å¼‚æ­¥ï¼šå¼‚æ­¥å‘é€emailã€å›žè°ƒæŽ¥å£å®šä¹‰ã€å…¶ä»–å¼‚æ­¥çº¿ç¨‹demo.zip','http://pan.baidu.com/s/1jIbOunO','icon-download-alt',0,'2016-11-03 15:24:06','2016-11-03 15:24:08'),(16,'JAVAä¹‹23ç§è®¾è®¡æ¨¡å¼çš„å®Œæ•´å®žä¾‹ä»£ç .zip','http://pan.baidu.com/s/1o8NJsL0','icon-download-alt',0,'2016-11-03 15:24:28','2016-11-03 15:24:29'),(17,'jd-guiåç¼–è¯‘jar_å¤åˆ¶æºç ä¸ºjavaæ–‡ä»¶(è‡ªå·±å†™çš„å·¥å…·).rar','http://pan.baidu.com/s/1dE8mrap','icon-download-alt',0,'2016-11-03 15:24:47','2016-11-03 15:24:50'),(18,'jqueryç‚¹å‡»å›¾ç‰‡é€‰ä¸­ç‰¹æ•ˆ.rar','http://pan.baidu.com/s/1jIJNEq6','icon-download-alt',0,'2016-11-03 15:25:12','2016-11-03 15:25:13'),(19,'jQueryè½»é‡çº§åœ†å½¢è¿›åº¦æŒ‡ç¤ºå™¨æ’ä»¶.zip','http://pan.baidu.com/s/1o8Lbz4m','icon-download-alt',0,'2016-11-03 15:27:51','2016-11-03 15:27:53'),(20,'luceneåŸºäºŽæœ¬åœ°ç£ç›˜çš„æ–‡ä»¶ç´¢å¼•Demo.zip','http://pan.baidu.com/s/1jI8suqy','icon-download-alt',0,'2016-11-03 15:28:11','2016-11-03 15:28:13'),(21,'luceneåŸºäºŽå†…å­˜çš„æ£€ç´¢Demo.zip','http://pan.baidu.com/s/1qXEB2DA','icon-download-alt',0,'2016-11-03 15:28:31','2016-11-03 15:28:32'),(22,'luceneç´¢å¼•â€”â€”å¢žåˆ æ”¹æŸ¥.zip','http://pan.baidu.com/s/1bo2kKCf','icon-download-alt',0,'2016-11-03 15:28:49','2016-11-03 15:28:50'),(23,'MongoDBçŽ¯å¢ƒæ­å»ºä¸Žå®žä¾‹ä»£ç æµ‹è¯•.rar','http://pan.baidu.com/s/1o7OiXMM','icon-download-alt',0,'2016-11-03 15:29:09','2016-11-03 15:29:10'),(24,'ormæ¡†æž¶ å¾®ä¿¡æ¡†æž¶ è‡ªå®šä¹‰æ‹¦æˆªå™¨ è‡ªå®šä¹‰æ³¨è§£ æ•°æ®æºåˆ‡æ¢ç­‰.zip','http://pan.baidu.com/s/1jHMVoiU','icon-download-alt',0,'2016-11-03 15:29:29','2016-11-03 15:29:31'),(25,'pdfæ–‡ä»¶æ‰“å°(freemarkerä½œä¸ºpdfæ¨¡æ¿ï¼Œé€šè¿‡å‚æ•°æ›¿æ¢ã€éåŽ†æ˜¾ç¤ºæ•ˆæžœ)ï¼ˆjavaç‰ˆ springï¼‰.zip','http://pan.baidu.com/s/1kUWw8Vd','icon-download-alt',0,'2016-11-03 15:29:47','2016-11-03 15:29:49'),(26,'pluplaodæ–‡ä»¶åˆ†å‰²ä¸Šä¼ Demo.zip','http://pan.baidu.com/s/1kV8Wpy3','icon-download-alt',0,'2016-11-03 15:30:05','2016-11-03 15:30:07'),(27,'SpringActiveMQæ¶ˆæ¯å‘é€ä¸ŽæŽ¥æ”¶(WEBç‰ˆ)-å…¥é—¨å®žä¾‹.zip','http://pan.baidu.com/s/1hsqL6vE','icon-download-alt',0,'2016-11-03 15:30:24','2016-11-03 15:30:26'),(28,'springbatchæ‰¹å¤„ç†(å®Œæ•´Demo).zip','http://pan.baidu.com/s/1hsgCgVq','icon-download-alt',0,'2016-11-03 15:30:47','2016-11-03 15:30:49'),(29,'springmvc mybatisé›†æˆâ€”å¹²å‡€æ¡†æž¶(ç›´æŽ¥ä½¿ç”¨).zip','http://pan.baidu.com/s/1i4XgFjR','icon-download-alt',0,'2016-11-03 15:31:09','2016-11-03 15:31:10'),(30,'springsecurityæƒé™æŽ§åˆ¶Demo.zip','http://pan.baidu.com/s/1i4MXsnr','icon-download-alt',0,'2016-11-03 15:31:30','2016-11-03 15:31:31'),(31,'spring_activemqæ¶ˆæ¯å‘é€æŽ¥æ”¶(æœ€ç»ˆç‰ˆ-éžWEBè®¿é—®).zip','http://pan.baidu.com/s/1jIDcfII','icon-download-alt',0,'2016-11-03 15:31:47','2016-11-03 15:31:49'),(32,'spring_security3_è¯¦ç»†é…ç½®(æ‘˜è‡ªç½‘ç»œ).zip','http://pan.baidu.com/s/1kUAbWjl','icon-download-alt',0,'2016-11-03 15:32:09','2016-11-03 15:32:10'),(33,'SSOä¹‹CASå•ç‚¹ç™»å½•è¯¦ç»†å›¾æ–‡æ•™ç¨‹.zip','http://pan.baidu.com/s/1hrY4BBm','icon-download-alt',0,'2016-11-03 15:32:29','2016-11-03 15:32:30'),(34,'WebSocketæ¶ˆæ¯èŠå¤©å®¤Demo.zip','http://pan.baidu.com/s/1jI7CzAY','icon-download-alt',0,'2016-11-03 15:32:51','2016-11-03 15:32:52'),(35,'è¯»å–Excelå·¥å…·ç±»(åŒ…å«æµè¯»å–ã€æ–‡ä»¶è¯»å–).zip','http://pan.baidu.com/s/1skE92ET','icon-download-alt',0,'2016-11-03 15:33:09','2016-11-03 15:33:11'),(36,'æ–­ç‚¹ å¤šçº¿ç¨‹ä¸‹è½½å®žä¾‹.zip','http://pan.baidu.com/s/1miuHeIG','icon-download-alt',0,'2016-11-03 15:33:31','2016-11-03 15:33:32'),(37,'åŸºäºŽdwræ¶ˆæ¯æŽ¨é€Demo.zip','http://pan.baidu.com/s/1boRTogJ','icon-download-alt',0,'2016-11-03 15:33:47','2016-11-03 15:33:48'),(38,'æ‰‹æœº_æ—¥æœŸæ—¶é—´æŽ§ä»¶.zip','http://pan.baidu.com/s/1hs7G2HY','icon-download-alt',0,'2016-11-03 15:34:05','2016-11-03 15:34:07'),(39,'æ‰‹æœºæ—¶é—´æŽ§ä»¶.zip','http://pan.baidu.com/s/1qXYou7M','icon-download-alt',0,'2016-11-03 15:34:25','2016-11-03 15:34:27'),(40,'æ•°æ®é‡‡é›†â€”åŸºäºŽç™¾åº¦æ–‡åº“çš„æŠ“å–Demo.zip','http://pan.baidu.com/s/1cpBMeM','icon-download-alt',0,'2016-11-03 15:34:46','2016-11-03 15:34:47'),(41,'ç½‘é¡µåŠ è½½è¿›åº¦æ¡-å®žä¾‹Demo.zip','http://pan.baidu.com/s/1pLViL9x','icon-download-alt',0,'2016-11-03 15:35:07','2016-11-03 15:35:08'),(42,'å¾®ä¿¡JSAPIæ”¯ä»˜å®Œæ•´Demo.zip','http://pan.baidu.com/s/1nvdehnj','icon-download-alt',0,'2016-11-03 15:35:29','2016-11-03 15:35:30'),(43,'å¾®ä¿¡JSAPIæ”¯ä»˜å®Œæ•´Demoç¬¬äºŒç‰ˆ(æ–°å¢žå…³é—­è®¢å•ã€æŸ¥è¯¢è®¢å•ã€æŸ¥è¯¢é€€æ¬¾ã€ä¸‹è½½å¯¹è´¦å•ã€ç”³è¯·é€€æ¬¾).zip','http://pan.baidu.com/s/1i4VKKNN','icon-download-alt',0,'2016-11-03 15:35:47','2016-11-03 15:35:48'),(44,'å¼‚æ­¥å‘é€emailä¹‹demo.zip','http://pan.baidu.com/s/1o7IJv1S','icon-download-alt',0,'2016-11-03 15:36:05','2016-11-03 15:36:07'),(45,'é“¶è”PCç½‘å…³æ”¯ä»˜demo(é€€æ¬¾ã€å¯¹è´¦æ–‡ä»¶ã€æŸ¥è¯¢ã€æ’¤é”€).zip','http://pan.baidu.com/s/1pKXAz0Z','icon-download-alt',0,'2016-11-03 15:36:25','2016-11-03 15:36:27'),(46,'æ”¯ä»˜å®PCåŠæ—¶åˆ°è´¦æ”¯ä»˜Demo-JAVA.zip','http://pan.baidu.com/s/1pLyEKsz','icon-download-alt',0,'2016-11-03 15:36:44','2016-11-03 15:36:46'),(47,'æ”¯ä»˜å®PCæ”¯ä»˜å®Œæ•´Demo.zip','http://pan.baidu.com/s/1kUAbWjH','icon-download-alt',0,'2016-11-03 15:37:03','2016-11-03 15:37:05');

/*Table structure for table `leave_apply` */

DROP TABLE IF EXISTS `leave_apply`;

CREATE TABLE `leave_apply` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `process_instance_id` varchar(50) DEFAULT NULL COMMENT 'æµç¨‹å®žä¾‹id',
  `type` int(1) NOT NULL COMMENT 'è¯·å‡ç±»åž‹',
  `reason` varchar(100) NOT NULL COMMENT 'è¯·å‡äº‹ç”±',
  `user_id` bigint(11) NOT NULL COMMENT 'ç”³è¯·äººid',
  `start_date` datetime NOT NULL COMMENT 'ç”³è¯·æ—¥æœŸ',
  `end_date` datetime NOT NULL COMMENT 'ç»“æŸæ—¥æœŸ',
  `create_time` datetime NOT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `update_time` datetime NOT NULL COMMENT 'æ›´æ–°æ—¶é—´',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='OAè¯·å‡ç”³è¯·è¡¨';

/*Data for the table `leave_apply` */

insert  into `leave_apply`(`id`,`process_instance_id`,`type`,`reason`,`user_id`,`start_date`,`end_date`,`create_time`,`update_time`) values (9,'2505',0,'èº«ä½“ä¸èˆ’æœäº†',1,'2017-03-31 00:00:00','2017-04-01 00:00:00','2017-03-31 18:08:57','2017-03-31 18:08:57'),(10,'2513',1,'å›žåŽ»è§å¥³æœ‹å‹å“ˆ',1,'2017-03-29 00:00:00','2017-03-31 00:00:00','2017-03-31 18:09:46','2017-03-31 18:09:46'),(11,'7507',4,'ç”Ÿå­©å­åŽ»~å“ˆå“ˆ',1,'2017-04-01 00:00:00','2017-04-07 00:00:00','2017-04-01 14:55:54','2017-04-01 14:55:54');

/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) NOT NULL,
  `total_fee` bigint(11) NOT NULL,
  `good_name` varchar(50) NOT NULL,
  `order_source` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

/*Data for the table `order_info` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `sort` varchar(100) DEFAULT NULL,
  `icon` varchar(30) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `permission_code` varchar(50) NOT NULL COMMENT 'æ‰€éœ€æƒé™',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`parent_id`,`sort`,`icon`,`type`,`permission_code`) values (1,'ç³»ç»Ÿç®¡ç†','#',0,'1','icon-cog','1','sys:mgt'),(2,'ç”¨æˆ·ç®¡ç†','/user/toUserListView',1,'2',NULL,'1','user:mgt'),(4,'è§’è‰²ç®¡ç†','/role/toRoleListView',1,'4',NULL,'1','role:mgt'),(5,'æƒé™ç®¡ç†','/permission/toListView',1,'3',NULL,'1','permission:mgt'),(6,'å¾®ä¿¡å¼€å‘','#',0,'1','icon-comments-alt','1','wechat:mgt'),(7,'æ¨¡æ¿æ¶ˆæ¯','/wechat/template/toTemplateMsgView',6,'2',NULL,'1','template:msg:send'),(9,'å®žæ—¶ç›‘æŽ§','#',0,'1','icon-laptop','1','monitor:mgt'),(10,'tomcatæ—¥å¿—å®žæ—¶ç›‘æŽ§','/socket/tomcatlog',9,'2',NULL,'1','monitor:tomcat:log'),(11,'æ¶ˆæ¯æŽ¨é€','#',0,'1','icon-rss','1','socket:push:mgt'),(12,'åœ¨çº¿èŠå¤©','#',0,'1','icon-headphones','1','chat:mgt'),(13,'nettyå®žçŽ°åœ¨çº¿èŠå¤©','/chat/toChatView',12,'2','icon-rss','1','chat:netty:service'),(14,'activitiå·¥ä½œæµ','#',0,'1','icon-stethoscope','1','activiti:mgt'),(15,'OAè¯·å‡ç”³è¯·','/oa/toOaApplyView',14,'3','icon-rss','1','activiti:oa:apply'),(16,'å·²éƒ¨ç½²çš„å·¥ä½œæµ','/activiti/toProcessListView',14,'2','icon-rss','1','activiti:process:list'),(17,'æˆ‘å‘èµ·çš„è¯·å‡æµç¨‹','/oa/toMyLeaveApplyView',14,'4','icon-rss','1','activiti:myprocess:leaveapply'),(18,'éƒ¨é—¨é¢†å¯¼å®¡æ‰¹','/oa/toDeptleaderAuditView',14,'5','icon-rss','1','activiti:deptleader:audit'),(19,'è¯·å‡è°ƒæ•´ç”³è¯·','/oa/toMyLeaveApplyTurndownView',14,'6','icon-rss','1','activiti:myleaveapply:modify'),(20,'æˆ‘çš„è¯·å‡åŽ†å²','/oa/toMyLeaveApplyHistoryView',14,'7','icon-rss','1','activiti:myleaveapplyed:history');

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT 'æƒé™åç§°',
  `code` varchar(50) NOT NULL COMMENT 'æƒé™ä»£ç ',
  `description` varchar(50) DEFAULT NULL COMMENT 'æè¿°',
  `parent_id` bigint(20) DEFAULT NULL COMMENT 'çˆ¶id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'çŠ¶æ€ï¼Œ0ï¼šå¯ç”¨ï¼Œ1ï¼šä¸å¯ç”¨',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='æƒé™è¡¨';

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`name`,`code`,`description`,`parent_id`,`status`) values (1,'ç³»ç»Ÿç®¡ç†','sys:mgt','å¯¹ç³»ç»Ÿæ¨¡å—çš„ç®¡ç†',-1,0),(2,'äººå‘˜ç®¡ç†','user:mgt','å¯¹äººå‘˜è¿›è¡Œç®¡ç†',1,0),(3,'è§’è‰²ç®¡ç†','role:mgt','å¯¹è§’è‰²è¿›è¡Œç®¡ç†',1,0),(4,'æƒé™ç®¡ç†','permission:mgt','å¯¹æƒé™è¿›è¡Œç®¡ç†',1,0),(9,'æŸ¥çœ‹ç”¨æˆ·åˆ—è¡¨','user:list','å¯ä»¥æŸ¥çœ‹ç”¨æˆ·åˆ—è¡¨',2,0),(10,'ç¼–è¾‘ç”¨æˆ·ä¿¡æ¯','user:edit','å¯ä»¥ç¼–è¾‘ç”¨æˆ·ä¿¡æ¯',9,0),(11,'åˆ é™¤ç”¨æˆ·','user:delete','å¯ä»¥åˆ é™¤ç”¨æˆ·',9,0),(12,'æŸ¥çœ‹è§’è‰²åˆ—è¡¨','role:list','å¯ä»¥æŸ¥çœ‹è§’è‰²åˆ—è¡¨',3,0),(13,'æŸ¥çœ‹æƒé™åˆ—è¡¨','permission:list',NULL,4,0),(14,'æ–°å¢žè§’è‰²','role:add','æ–°å¢žè§’è‰²ä¿¡æ¯',12,0),(15,'ç¼–è¾‘è§’è‰²','role:edit','ç¼–è¾‘è§’è‰²ä¿¡æ¯',12,0),(16,'åˆ é™¤è§’è‰²','role:delete','å¯ä»¥åˆ é™¤è§’è‰²',12,0),(17,'æ–°å¢žæƒé™','permission:add','å¯ä»¥æ·»åŠ æ–°æƒé™',13,0),(18,'ç¼–è¾‘æƒé™','permission:edit','ä¿®æ”¹æƒé™ä¿¡æ¯',13,0),(19,'åˆ é™¤æƒé™','permission:delete','å¯ä»¥åˆ é™¤æƒé™æ•°æ®',13,0),(20,'æ·»åŠ ç”¨æˆ·','user:add','å¯ä»¥æ·»åŠ æ–°çš„ç”¨æˆ·',9,0),(21,'å¾®ä¿¡å¼€å‘','wechat:mgt','å¾®ä¿¡ç›¸å…³åŠŸèƒ½å¼€å‘æ¼”ç¤º',-1,0),(22,'å¾®ä¿¡æ¨¡æ¿æ¶ˆæ¯','template:msg:send','æµ‹è¯•å‘é€å¾®ä¿¡æ¨¡æ¿æ¶ˆæ¯',21,0),(23,'ç›‘æŽ§ç®¡ç†','monitor:mgt','ç›‘æŽ§ç®¡ç†',-1,0),(24,'tomcatæ—¥å¿—ç›‘æŽ§','monitor:tomcat:log','å¯ä»¥æŸ¥çœ‹æœåŠ¡å™¨ä¸Štomcatçš„å®žæ—¶æ‰“å°æ—¥å¿—',23,0),(25,'æœåŠ¡å™¨æ¶ˆæ¯æŽ¨é€','socket:push:mgt','æœåŠ¡å™¨æ¶ˆæ¯æŽ¨é€',-1,0),(26,'åœ¨çº¿èŠå¤©','chat:mgt','åœ¨çº¿èŠå¤©',-1,0),(27,'nettyå®žçŽ°åœ¨çº¿èŠå¤©','chat:netty:service','nettyå®žçŽ°åœ¨çº¿èŠå¤©',26,0),(28,'activitiå·¥ä½œæµ','activiti:mgt','activitiå·¥ä½œæµç›¸å…³æŠ€æœ¯',-1,0),(29,'OAè¯·å‡æµç¨‹','activiti:oa:apply','åŸºäºŽå·¥ä½œæµå®žçŽ°OAè¯·å‡',28,0),(30,'å·²éƒ¨ç½²çš„å·¥ä½œæµ','activiti:process:list','å·²éƒ¨ç½²çš„å·¥ä½œæµ',28,0),(31,'æˆ‘å‘èµ·çš„è¯·å‡æµç¨‹','activiti:myprocess:leaveapply','æˆ‘å‘èµ·çš„è¯·å‡æµç¨‹',28,0),(32,'éƒ¨é—¨é¢†å¯¼å®¡æ‰¹','activiti:deptleader:audit','éƒ¨é—¨é¢†å¯¼å®¡æ‰¹',28,0),(33,'è¯·å‡è°ƒæ•´ç”³è¯·','activiti:myleaveapply:modify','é‡æ–°ä¿®æ”¹è¢«é©³å›žçš„è¯·å‡ç”³è¯·',28,0),(34,'æˆ‘çš„è¯·å‡åŽ†å²','activiti:myleaveapplyed:history','æˆ‘çš„è¯·å‡åŽ†å²',28,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(24) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(50) DEFAULT NULL COMMENT 'æè¿°',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='è§’è‰²è¡¨';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`description`,`status`,`update_time`,`create_time`) values (1,'è¶…çº§ç®¡ç†å‘˜','æœ€é«˜çº§åˆ«æƒé™ç®¡ç†å‘˜',0,'2016-08-01 15:17:52','2016-08-01 15:17:53'),(2,'å‘˜å·¥','åŸºå±‚å‘˜å·¥',0,'2016-08-01 15:18:21','2016-08-01 15:18:23'),(3,'è´¢åŠ¡','è´¢åŠ¡éƒ¨è§’è‰²',0,'2016-08-03 13:21:56','2016-08-03 13:21:58'),(4,'äººäº‹','äººäº‹',0,'2017-03-21 13:19:34','2017-03-21 13:19:34'),(5,'éƒ¨é—¨ç»ç†','éƒ¨é—¨ç»ç†',0,'2017-03-21 13:20:47','2017-03-21 13:20:47'),(6,'å‡ºçº³å‘˜','å‡ºçº³å‘˜',0,'2017-03-21 13:22:06','2017-03-21 13:22:06'),(7,'é‡‡è´­ç»ç†','é‡‡è´­ç»ç†',0,'2017-03-21 13:22:23','2017-03-21 13:22:23'),(8,'æ€»ç»ç†','æ€»ç»ç†',0,'2017-03-21 13:23:03','2017-03-21 13:23:03');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=758 DEFAULT CHARSET=utf8 COMMENT='è§’è‰²æƒé™è¡¨';

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`id`,`role_id`,`permission_id`) values (226,3,-1),(227,3,1),(228,3,2),(688,5,-1),(689,5,28),(690,5,30),(691,5,32),(720,1,-1),(721,1,1),(722,1,2),(723,1,9),(724,1,10),(725,1,11),(726,1,20),(727,1,3),(728,1,12),(729,1,14),(730,1,15),(731,1,16),(732,1,4),(733,1,13),(734,1,17),(735,1,18),(736,1,19),(737,1,21),(738,1,22),(739,1,23),(740,1,24),(741,1,26),(742,1,27),(743,1,28),(744,1,29),(745,1,30),(746,1,31),(747,1,33),(748,1,34),(749,2,-1),(750,2,1),(751,2,2),(752,2,28),(753,2,29),(754,2,30),(755,2,31),(756,2,33),(757,2,34);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL COMMENT 'ç™»å½•ç”¨æˆ·å',
  `password` varchar(50) DEFAULT NULL COMMENT 'å¯†ç ',
  `real_name` varchar(20) DEFAULT NULL COMMENT 'çœŸå®žå§“å',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'è´¦å·çŠ¶æ€',
  `open_id` varchar(50) DEFAULT NULL COMMENT 'å¾®ä¿¡openid',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`password`,`real_name`,`status`,`open_id`,`create_time`,`update_time`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e','è¶…çº§ç®¡ç†å‘˜',0,NULL,'2016-08-03 13:22:46','2017-03-31 18:16:39'),(2,'yuangong01','e10adc3949ba59abbe56e057f20f883e','å‘˜å·¥ä¸€',0,NULL,'2017-03-31 18:13:27','2017-03-31 18:13:27'),(3,'bumenjingli','e10adc3949ba59abbe56e057f20f883e','éƒ¨é—¨ç»ç†',0,NULL,'2017-04-01 11:36:59','2017-04-01 11:36:59');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='ç”¨æˆ·è§’è‰²è¡¨';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (66,1,1),(67,2,2),(68,3,5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
