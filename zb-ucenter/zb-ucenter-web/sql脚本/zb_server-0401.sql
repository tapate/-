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

insert  into `ACT_GE_BYTEARRAY`(`ID_`,`REV_`,`NAME_`,`DEPLOYMENT_ID_`,`BYTES_`,`GENERATED_`) values ('2502',1,'leave.bpmn','2501','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">\n  <process id=\"leave\" name=\"My process\" isExecutable=\"true\">\n    <userTask id=\"deptleaderaudit\" name=\"部门领导审批\" activiti:candidateGroups=\"部门经理\"></userTask>\n    <exclusiveGateway id=\"exclusivegateway1\" name=\"Exclusive Gateway\"></exclusiveGateway>\n    <userTask id=\"hraudit\" name=\"人事审批\" activiti:candidateGroups=\"人事\"></userTask>\n    <sequenceFlow id=\"flow3\" name=\"同意\" sourceRef=\"exclusivegateway1\" targetRef=\"hraudit\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${deptleaderapprove==\'true\'}]]></conditionExpression>\n    </sequenceFlow>\n    <userTask id=\"modifyapply\" name=\"调整申请\" activiti:assignee=\"${applyuserid}\"></userTask>\n    <sequenceFlow id=\"flow4\" name=\"拒绝\" sourceRef=\"exclusivegateway1\" targetRef=\"modifyapply\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${deptleaderapprove==\'false\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"flow6\" sourceRef=\"deptleaderaudit\" targetRef=\"exclusivegateway1\"></sequenceFlow>\n    <exclusiveGateway id=\"exclusivegateway2\" name=\"Exclusive Gateway\"></exclusiveGateway>\n    <sequenceFlow id=\"flow7\" sourceRef=\"modifyapply\" targetRef=\"exclusivegateway2\"></sequenceFlow>\n    <sequenceFlow id=\"flow8\" name=\"重新申请\" sourceRef=\"exclusivegateway2\" targetRef=\"deptleaderaudit\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${reapply==\'true\'}]]></conditionExpression>\n    </sequenceFlow>\n    <endEvent id=\"endevent1\" name=\"End\"></endEvent>\n    <sequenceFlow id=\"flow9\" name=\"结束流程\" sourceRef=\"exclusivegateway2\" targetRef=\"endevent1\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${reapply==\'false\'}]]></conditionExpression>\n    </sequenceFlow>\n    <exclusiveGateway id=\"exclusivegateway3\" name=\"Exclusive Gateway\"></exclusiveGateway>\n    <sequenceFlow id=\"flow10\" sourceRef=\"hraudit\" targetRef=\"exclusivegateway3\"></sequenceFlow>\n    <sequenceFlow id=\"flow11\" name=\"拒绝\" sourceRef=\"exclusivegateway3\" targetRef=\"modifyapply\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${hrapprove==\'false\'}]]></conditionExpression>\n    </sequenceFlow>\n    <userTask id=\"reportback\" name=\"销假\" activiti:assignee=\"${applyuserid}\"></userTask>\n    <sequenceFlow id=\"flow12\" name=\"同意\" sourceRef=\"exclusivegateway3\" targetRef=\"reportback\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${hrapprove==\'true\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"flow13\" sourceRef=\"reportback\" targetRef=\"endevent1\"></sequenceFlow>\n    <startEvent id=\"startevent1\" name=\"Start\" activiti:initiator=\"${applyuserid}\"></startEvent>\n    <sequenceFlow id=\"flow14\" sourceRef=\"startevent1\" targetRef=\"deptleaderaudit\"></sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_leave\">\n    <bpmndi:BPMNPlane bpmnElement=\"leave\" id=\"BPMNPlane_leave\">\n      <bpmndi:BPMNShape bpmnElement=\"deptleaderaudit\" id=\"BPMNShape_deptleaderaudit\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"250.0\" y=\"220.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway1\" id=\"BPMNShape_exclusivegateway1\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"535.0\" y=\"227.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"hraudit\" id=\"BPMNShape_hraudit\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"620.0\" y=\"220.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"modifyapply\" id=\"BPMNShape_modifyapply\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"503.0\" y=\"310.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway2\" id=\"BPMNShape_exclusivegateway2\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"535.0\" y=\"410.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"endevent1\" id=\"BPMNShape_endevent1\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"890.0\" y=\"413.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway3\" id=\"BPMNShape_exclusivegateway3\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"770.0\" y=\"228.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"reportback\" id=\"BPMNShape_reportback\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"855.0\" y=\"221.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"startevent1\" id=\"BPMNShape_startevent1\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"140.0\" y=\"230.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"flow3\" id=\"BPMNEdge_flow3\">\n        <omgdi:waypoint x=\"575.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"620.0\" y=\"247.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"575.0\" y=\"247.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow4\" id=\"BPMNEdge_flow4\">\n        <omgdi:waypoint x=\"555.0\" y=\"267.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"555.0\" y=\"310.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"555.0\" y=\"267.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow6\" id=\"BPMNEdge_flow6\">\n        <omgdi:waypoint x=\"355.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"535.0\" y=\"247.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow7\" id=\"BPMNEdge_flow7\">\n        <omgdi:waypoint x=\"555.0\" y=\"365.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"555.0\" y=\"410.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow8\" id=\"BPMNEdge_flow8\">\n        <omgdi:waypoint x=\"535.0\" y=\"430.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"302.0\" y=\"429.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"302.0\" y=\"275.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"48.0\" x=\"361.0\" y=\"438.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow9\" id=\"BPMNEdge_flow9\">\n        <omgdi:waypoint x=\"575.0\" y=\"430.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"890.0\" y=\"430.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"48.0\" x=\"659.0\" y=\"437.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow10\" id=\"BPMNEdge_flow10\">\n        <omgdi:waypoint x=\"725.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"770.0\" y=\"248.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow11\" id=\"BPMNEdge_flow11\">\n        <omgdi:waypoint x=\"790.0\" y=\"268.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"789.0\" y=\"337.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"608.0\" y=\"337.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"672.0\" y=\"319.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow12\" id=\"BPMNEdge_flow12\">\n        <omgdi:waypoint x=\"810.0\" y=\"248.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"855.0\" y=\"248.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"810.0\" y=\"248.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow13\" id=\"BPMNEdge_flow13\">\n        <omgdi:waypoint x=\"907.0\" y=\"276.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"907.0\" y=\"413.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow14\" id=\"BPMNEdge_flow14\">\n        <omgdi:waypoint x=\"175.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"250.0\" y=\"247.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),('2503',1,'leave.leave.png','2501','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0�\0\0�\0\0\0\r�Q\0\0\0:IDATx����\\e�\'�E���0˰���˲�.�.�f]FAA��ө\\ 1�@`0KC�� K��	xAP@��u��*H&CV�a��\0�Kb ����t��R�N���������y>u��T7�����{�sii\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0`�U*�~��k֬�]�X��|��Z�����ʕ+��L�Ju\\\0\Z(:W�V���[�����/kM�֯__���[�M�����\n���8�4�Z@�D�ֹj�N�˩���_*������j\r#�\nds���zſT@W�1���\Z$9Q���U�/P��qji��&�`��7OV����}�gf-��9EUP�5u�Z�U`���Y[��G+�t�I��x.^SXmC�P��qu�Z�}hx�~���6�\\��W�]��*�0VJ�R}T��quܠ�A-;\n4�h�_��A;X�ªh�80�gR[R}<DW��q�Z�4�*а�}�͝��xMaU�a�B�!5����5,���A-�Z�}�	����˗/��E��X�p���s�n�1cFeڴiY�3gΫ_��9��3�:����|���j:X�60@H����:��Բ�j�U���ŋ?q����̙3�r�k���}��ٍ�_}��JnÆ\r�x�����r��Vb��O�3eʔl����`E;^SXmh������\'���:nPKP�쫌S���vꩧ���/|��+V�Ȃ�plܸ��z���W���ϝ;����>�Ӷj�����\rZ��5�Uц�䭅�I�	W��q�Z�4�*���u�Q�|��߭�����u��WRP�x�1�\\����[x����wW���雏l���5�UцQv�0;��]_Wk�q�Z�4}.\Z�SN�Ʊ���O���Hz��*˗/�8{��\'RX�[zt�v�G�d���)��64($�\r�{~2�eu\\7�ePK��2�B�y�~����/��w3g�|6�������z�ʃ�p��#��xMaU���B�H�_��q�Z��0r�p�I͐����k_Jay����)�qs�_���0�x-�Q\\ma[;ǱѿG��q�Z�쫰���]qN�]w�Ui��}�{OL�>�:W�������*����\\�-։upEF8$��ӄ��A-�Z�}�q��SN�\'.��h������m�O`����\n��h�(�?g�R;��8��B���A-�Z�}�&�I�[@��խ�k�ڵ�N�>�_\n���>��+�C)�[*�\n��\r#�I�����*�ǡ����7!ò:���2���Wir��~�?^u�U��r��gߗ��Y>��+��ڹʛB�h���j�Rӆ�kCrގ�y}�ީ����ji�U�Xgg�;fΜ��\r6�YP^�v���\\��+ښ�\r\r6Xx(�n�>�����:�SW�\rj���4�.�ଅV�ڗ�������_}\":X�64N��7oc���9�C\r�C\r�������6U��q�Z�\\�}�&�hѢ;����1�K�.]��k,E\Zkڴi�hQ{���>4ķ\r5�n�>ܐ\\���7k\'|��TW�\rj�si�\\4�������<(�Y��Ա(��`)����S�n�J�q�3E�u�_��!����xݦ�:nPK�K�碉͝;w�u��<(?��SO�b��N���\rcZs�;�Ԟm=�r�N�Ϸ#$o�OSlSu\\7��ϥ�s��f̘Qy��W+� ��N���\rc_sj;�ՙ�����	ߞ�<���ۦ�:nPK�K���;I�BP���Hm�����v����%�Y�^7@�����ǒ���+̘9&�U��6��ϥ	ʘQrPֆ�F����7������va����SOe���\'?��^xa��}��l�x�w�Q���k+?��*���g��x��+��_�#�H���D���{lg-����������:\'�xb�r\\�._��I�f��ݞ:���ܶokڞ��p�ߺ��w{Oi���⬎������|z����������1��(��/-�רm�,����Ν;���~��}��也��g�.O�O�Z�eA�r�����p�r�;X�W�����9眓}�t�MYh�s�=�P�Ԟ��l�_|1{|��P�w�}+=�Pj?��O���#�8b��A��o}k��.�}׻�5��.��R���>6`G��.��[o�5�]�7�q��v���~�r����l�{.������/�|�eE�I\\s�r}(}�et�ܖ��������q�7�����ڻK�j���sW������ŏ�׿��]^~K#�i3�C=t�ף�G��쮻��~��_�~ǯ~�����tF٠�$�\Z��|�y�m�܊+*{�G���Ol�ZL`p������������y睕�ӧg?���2��i�����Y�z�J�������h���?��(�~�1:4�ׯ�dD?f\r���yh�mo~������3�d��;�uݙ3gf����u�]��0��N;U,X�u���V�?^��B��D��Y0��@ꩧf�������=���w�+�\0_\Z�6����V($ ����N��-_ h8��/�0�x��[w��v����G,�}����ɱα�z߹���ɬS�Br��������w���c��i3��[Z\'������͂���˳�_����.O��lPk�\rj\ru�}&�m������د��������v�m��#�<ҿ��G�}7A�I)�|�76�}�S���Nk�;XQgϞ��X�sb�����Hd��]�A9֍Ώ�������s��㕯s��W��\'f\Z����~w������}��l�3��_�Y�X��:�3�㋤�:����$�9[�l��;̐��[���O� ����p{wyF>;5���V����t����be��=�Q۴:�QWc����o��z�Q��>�����b96�g���.O��lPk�jm�E+�SE_*߿c@+�r�)���O�����>Y�ς2�J�y�]�x��y��ݛ\n����u�b�0��s�\"G�p��E\'%��Ԟ���\\߭�)���B{�%�l2��љ�/���{o�\\��8(�{ⰽx�V���\"�;�ѡ��8ǭ~�#_?�_�/�|  ���{oE���;��ܲ�a9�);l����������ҩY^�����wK�B��	ˊ�N��wгNzWiN���{�����0Em�(��š�q�O�֨��b9j�@��Z��yP6�5���:����n�N��S�b�)�S�b?l��Q��b\0+�����?������k&��N:�O�<����E�����\n��oS\0z�O������0-:\'�d�<,��\'(G��x�����]-d�!߃�^V����/d���:/��W�x�g>���V�Z��s��ۯ�搢����)ڰ͝������\r�c��/���N�ё���2�����X�@}xGOi�ҷ�c�ގ�����X,,G�{�s�����ѱuy��q�Z`_�Z�~V>��Z>H���|g�Ɏ��8���z�_K�4�h_������f��̤v��\'��-��2fAyٲew���}�\'��������b^hk�:�wZ����=\'���p�������7o�&�\'Gg.�;��k��:d���X?)�=�0�����Ծ7���fUm�^?ctd��o)3P\'��at�[��hq��8�3��p\0�٫��r��Qt�cV���=�w�e�������ڣ}��w-������V����O�i��<��A�	�c\"!���>�hѢl���￐^~\r�X?����j�b���}�蓕��MNw���N9�\'�����MT�I_�>}��̘1�O}�/���c��âc$1/�y0nP��.�(;�,By^̢W�;�mE�o���g)Z�p{�x���|�b�w�:p[;.����-ՙ�|�@цa���s!k;�ù��`�ҹ�q�b\\���/��j���@=�o���:�������<4GH��:����������F�����y(3�Qg�V�.O�:nPk��>]�@S�r3�r��轖.�����ũq(v~���f����Ν�T�h�-�{ڴi=>��)�qѕ(�q�Yޡɯz���q���.��<T�ߏ3?���3��~��w~���r��|v#���sg����g.�[\\�;�pŗH>��i�Ԝ3���Sц!�����j�y�Wǭߐou�������Ύ�������s��|����tnυ��S�;�\\�<�=�㵘Q�7kg��1f~�x\\�\"�+�K]V�\rjM䠜�3�)o�\\ۇ�?��>(G�+�}T�\Z�ɜ9s>~��Go�/�FI_~\n�nmm}�O`l�r~��ڙ���/�P��[G���8�!���x���b���{j����{�����|A䇌�ⵖ���v�⵸xXb��Tц�\n�#ѩ_?��oB�����g��:0ح��A��Su����O?}X�f���A���[��K����A��n\'�R3?�|�Ƀ��e&�c�9��.]�J#�N;�q��ԩS��\'v�n�9w�6L��<ҡ[W��q�Z~_�ɇ�d�����p���A9�����G�3͵�A��2i���?���?r�%�l͐�nݺWgϞ�D�Ph��u�&jS��Ć<�4J�W�5uܠ�}u�w?������Y�6\\u�U/��Lr5$�ek+�:X0���p;�C:�Q��q�Z�UM����+PϜ9s�E]��H���[�IV�m��;�4�g��quܠ�}U��2���r\n���հ}�ї��Pqu�p�s�mE�����-��wU��q�Z�U;JSikk����_��W�y衇�5���SO�[�|��1�<mڴ\\�Z�V�aR:�\Z�����\'SHV��q�Z�U;�7����3g���9�����=�أ��xÆ\rO�Y�殥K���7o޽�B�)h+�mE&��:�jfJW�\rj�W5�*T\n����R{$�WR�Tۓ�ݖ��R�2{��7�b���\r�\'mHV��q�Z�U;\n(ڊ6P��^2�C�:��Բ�j�U@�V��Z�QH-��;;;;_�-�����oT�5uܠ�}U��mE&�jPδw�;���y�y���/�}W�h��M�0Y����r��./�����L����\rj�si�U@��mhTP�)}���{x�c���3o�l��_�����Q�#T��.����.?��8O�������������e_�����hC���	=�=\"�w����J���ݾd����Y�!Bq,wt��H��-�}c�x�����qMy|��i_}I�˾j_mMц��@�]:�}Iy��ωí����vt����1c��� ���hr��]�x��]�K�qMy�f�zs�W��e_���F�XT������+����A9��@��U>-�K�wQx�*��g��^�=`��[wςs\n�}�O;��Ʒź긦�����ַ�}�7���,(��ʕ+�\\�n��ؤm�ڵ��������A9f�������ècf�F��|]H�)�xAW�q�r�S�����0lu\\S�G%(�Q�W7��0�eP7J��gn���<�����@6W���Uoo}οT<(�c����w�>��S>�?//�����n���qѯ���ٵ�k������:>�f͚��i_}��A-�Z�����I���E�CN�k��+*�i��5->���dZP���1f���^��q������uO���{���u��y²�c�|�����H��:>b��Im��	�Z�\0�����4O�2e[&�Baz%f�;zJs����pN��zʇ�լ�P����C��gg��J-�SNa���(�����=fKL�A�	2AaP�����>���+Q���A�L���@�kmm}O�W�%�`\0\Z���b]m��\"��4��ӧ�����P�hl��\'�M�[\n·�2�������Ӿ�3[B\r��E��ڐ��*;WtҀ��{�}�[B\r�qz���\n��l!�I������Na�m	5���\n��v�-:i�����~�\Z@c�x_�BPv�2�c����\Z[B\r�1���-�dW��4`�M�:u�����-��`�y )\nM��-��\Z��\r�灾�����kK��\0(؀}h�n���ȖP�P��<В��),�`K��\0(؀}��O?�ڵ��\Z��\r�灾����]eK��\0(؀}h��Qn-\n߷%�`\0l�>�d��*�}�r[B\r@���@�~:+�Km	5\0��}����.�%�`\0l�>�d�^����[��\Z��\r�灾����.�%�`\0l�>��Ǧ�̖P�P��<В�j~�PXjK��\0(؀}h��Q>!��sm	5\0�����Ƿ�n�����SP�[B�\0��{��^�RPN��Cl)h�}��S[lK�w�`���ߴ��|۔)Sv���������B�p�-����\r��>��`A9u����)��3S[hK�w�`���t���f��i���q��-����\r��~�ٹ��M���z��]\0(���v��6��C\n��+�l	�.\0l`t��j�M>����K�>;ߖ��@�F��U6�M��.K�X[B�\0��}�<4�����\n�/��]\0(�Д*��?���5k���X,Vn��f�	Zoooe�ʕ�Nm��L���S;ʖ��@���!yժU�u��U^~�e������+��z�)4οT&�w�ũͶ%��\0P��)�L����a��o��~�R�`�͗\nGK�w�`Cs�í��n)(��_*I\n�W���M�%��\0P��)����h�eu������r�-����\r�:(��7OV����}�gf-��9AVP�m�n�*����]\0(�0n���Ϭ��������MZ<�	��2G[[�u�B�P[B�\0�mP~���6�y��}���2������%��\0P�a����3hP�ׄYA������?nK�w�`ø\r����9hP�ׄYA�Q:;;_�m���m���n.\n����]\0(� (k��vj�.�w,�����_�����:��֮7Y�����A�<wy�-�ݥe�^�H!�֩S�~Ė��@��q��*׃�xM���{J�8�{�.)�A��e���[>����W�4��|T����\'���K���.γ����SP�ߖ��@��q�\\�lР�	���h;���G����b�x~W��ðۗ��5;;�;D(����)0����o��w^\\~���)��פ��-����\r�6(oX{w�����a��xM��%���ۗ��N!��8�:Z{OiaGWiN�3���q\n�i���!��ŏ���U��Vl���;�O��A[B�\0�mP����l��9AVPn�8�:q{W���������U���(���{��������)�v\\��o�u��M��|W[[��%��\0P�a��^�<�l~�uz.^f����q�t\n���a�13�?��]��?$���p�����8W9�)D��r~���)���IAy/[B�\0�eP~����_����x-�h���7B��]����������_>��[<��q\\����xv��X���}�dS|7�_(��%��\0P�a|�^�<�`�r��zА��X\'�5�,(��8�8cf8.�?W�Ξ��\\�??���z\\w�\',+�;����^���c���`kk�{m	�.\0l7Ayk��f��F�Or�쎞��,0�0�S����aq5�8Ժvv�����٭�R��SX~���)��NA�=��~\0\n6���<�Y�-�.��2l����v�%��\0P�a��m\r�yne��w�3g��Ֆ��@��q�5AF��yݬY��ؖ��@�AY�����>mmmW���8�n�����G��~\0\n6�ڈ�ȩ]��+j����3f�͖��@�AY��\\���_3����C=�m	�.\0l���\n�mmm�C�����8�n~����і��@�AYۦv�WT�A���ͯ����ޖ��@�AYۦ��p�i㵩��]\0(� (׵\r6ľYy�g*/��b��;�̞�m�ry���{�9��SO=����ŋ�������ʮ��:��?���*w�u׀��.k�C��N�z`�i�	�~\0\n6L�����}�Ȃr���x����|ݷ���Y���n������__���{���Tn��ly�����y��������ʻ�����W�Z5`(��u�\r7T�8��=��E]T��~�-�},/�U(J�m���w�`��ў}����z�%�d����׋`��Օ=���|\'{�>}z���/���W^�Y���>�ޅVv�i��i�����g��`�}����$(�����`����0���.\0l�A9f}W�^��Ϙ��Cm>c3�<��&A9���:fcf8�����,(��~F�����y(���=y��^�|�w4MP�Ug�o���@��q\Z�k�j�;�/���T��/�2;���?��&A��G�\\x��s��o��b8��ԧ>5���Ҍr~t� ���y�q�u��b���bV9���\0�]\0(�0A�r�<���(���ٹ��ܯ~�����ؿ���x}�ܹ�kf�;�\\�܊+����o�M�U��[e\0�.\0�Q�q��#�<��q~��B]��O<�=F��hq��G>�l9�����/lӌr��w�}w塇ʖ>��M��@\0���\0l�G4(��\ro�.Ε��,o<ơ�-53ʵ�=�8<��6[7~�`W��Y�~��;��֧L�\"(�w(� (76(ǬnK��N�}��r\r�cַ>���<�Z��W�Η����-(�w(� (�Z�s��0����P��E�joaw�]v���^q�y��e?���ޓ�n��E��s�=�����3�l6s�:�3���lA@�@�AY�&(�]\0(� (k�2����\r��&(�]\0(� (k�2����\r��&(�]\0(� (k�2����\r��&(�]\0(� (k�2����\r��&(�]�]\0\n6ʚ����`���	�\0�]\0\n6ʚ����`���	�\0�]\0\n6ʚ����`��U,���n/������~\0\n64�ʕ+�\\�n�@ڤm�ڵ�������]\0(�� �R�3��r�o�~��L�k&9Broo}οT@�\0\Z(�Cn������8v<������C��gq���w�`�y\05\0����\0(؀}@\r@���\0j0\0\n6`�P�P��<�\Z��\r���`\0l�>�\Z�`�y\0�`\0���(؀}\05@���\0��\0\n6`�@\rP��<\0j0��\r��P�l�>�\Z�`+�`�@\r@��<\0j0\0\n6`�P�P��<�\Z��\r���`\0l�>��`�y\05\0����\0(؀}@\r@���\0j0\0\n6`�P�P��<�\Z��\r���`\0l�>��`�y\05X\rP��<\0j0��\r��P�l�>�\Z�`;_��V���~\0\n6L��{��^�RPnkk;Ė��@��ɴ�ߴ��|۔)Sv��\0��\0P�a2������a����_��\0��8��n6`Tk��_\0e`<u�t�\0\ZR�� (�*��&4����\0Ah������A�@��ZN��f��4��:�@P���v����\Z��/\0�2м��J���e��\0�@:0�gR[R}t�@c�\\N��&�y8�ٌ��2��+\n;����.ִ��VN��y=��J�|+�����g�\Z���mH���\0l��S�X=��-�;y���-��f�e`���0���|9�m�K�U�of8���̶*���nH�Z6���R�=���O3f̨�u�Y�+VT��ʓO>Yy��+a�ƍ�u��U~�_T���l�Y�f\r�oJ��Oma\0A9�o�x��Lj���o���ngϞ]��˲ </��B�k�����W���Lz�bk��ȅ�C��}��\0C\nɫjC������ꫯVn���lF��w?���lu\0Ah|H��L���&3�q�u�ܑ�v���I\'�T�_lkkS�e`l��]��=\0N�����f͚�h�s����ڙ�����	\0�����H6mf`�>�>����Z�x�&�_�{4�$\0e`�6��ڑ����|�hkk���s�G�p��<��s���>��_K|\0�20��|HM��vj���P��`����S�X{u��p�p���?���ן�D\0e`h!9Y\r��a��ڐ��#j^w�2��4m��yX�[@5ڹ�[������\r^\n�[\n���\0�;g�����t����MýO�Hx衇6����ٳ�����C�A���塆����0l`\"���S��[Z\'�iyH�+Q��������������������pCr��_�bf�@��j�9�\r���\Zu��-���+]�@P	�C\r�����mcH�9g�pA���T?Ü�+�?��c��͵�ާ (���m�t�����������A�?��9��x�|#�v]�����>=��|A�>�WS{%������KՋW�kj/����m	�M�7q1���/�mH�ש�Om]jO��TjO��6�_U�dK���~��#�=��C�=��/R�y�Rz =ޟ����}�����I����O�{ޙ�OS�#��S�Ij�\'�5�ݖ�?�����Jme�=���[bD8-��c1���L�?J��1��ڍi�������i���xmjפvUj?H��]z\\�����xej���H������.M���w��wR�8-_���څ���J��L�_O��v~Z^���֓���cWz��8�*=���xn����I����N�g����������(=��;S;=��?���Ծ��OK��^?5-����OJϝ��OH��rGZnO�����i���xlz����RZ>&-1=�I�����xTj�����i�#��<3-�H���r�znWzhkM��KO�eZ><-6αJ˟I˟Nˇ��C�O����xp�����������[s����������G��G�sDg&-�KZ�p>���K�����>������/-�sZ��3f����^i�?�����H��!=���޿O��M��.-�yZ~Oܒ#-�;=��������9s�i�O���5k֬?N˻��_����w����巧���ӟ��[��[�zo>��C�0-�?|����s��yÔ)SvH˯���|]5�n��@ay{Br}Xv�20�r�s��/����7n����h�?��ytң���輧���#���G?:������Gq�;��`�\n\"<D��0�\"�EZ��ՠ�g<��J\"�DH�����c5���2h\"�Dȉ��\'�O����o5�)�R��O�\"LE�J�S��c�\"xE�0�,�Y���ڧ\"�Ep�\0W\rr��p!/�^���<�\Z\0�E �Ù#4Fx���?_\r�GU����yL���J��j@=>k5�.�0�6�m�ܴ|r5�\Z�\Z��R\rǧGX���3�:�����#lG��!<�xZ>�\Z̗FP��e�~��j��F5�G࿰:\0pq\nT.����|Yu����@��VVT~�i���@ĵՁ�뫃7F�:���~\\�荁���-�A���A�Y](��:p���)wTW���Ux��{b@�:0su��������������_V}���\n=Y z�:`��:��/�A��� S���U�~[�������A�W��V��`Egh{�E��]?������c�:���i��5A\0��֮r��A���g��r����Qƽ-���c�<&�(\04��Ϸ��9�����LԠ��k�q�2\0@�����v���z=�����Dʷ�u3jr�:�z\r\00���B����,f��Q&VP�ly�uz��>��k�}�O\0�5[���ZHn�ư��}��\0�L����Yg�5fA���N�\rʟ��\0\0lZ\n�C\r��\r�����I�z玸]f%-W�{>?�ŸӈO\0`s1�\\��-ÿO�@a������&����7nm�/��j������(]�y�W]uUÃ�\\P�/��\0��Nh��F������8�_�JX���Wm�JHn����j��.߱�����)��/)･\Z��U�4�����6wy�-}�+w��.}4�}��]ۻʧ�r��8aY��]�#ۻ�\'�_ڻoGOin�n����o��\'�dT{��ܹsz���k�f3��ߟ>}��>\0FE���wt����|Ht��Z���SYd�3�ϾDgq���~���U�k��mO�����v@��/�ߝ:��\Z��ΤSv��#Z�˧��m����Q�:z���ա�Q���E�I=��c�8jF��σ삮�\'�=���O�)��_���K�:��ݲ�󬨃�=�Î]����o����׿��!�W]��|2LV��)ߓ��k���aA��3���mmmW�4\0ݽ�-���)����(5���������]�v��A�F�,p���{ۻ���p���N�r�n��ˋbF&:�Y��|]�{�Gu�W�ͧ����5�a�4Pԟ<�F���U���wQ7�jUԜ�Qò��]>����b����E�f�S���;������N_]*��դ�=��}�;=�U:�\'�dV;�3����/G=$�x㍵�\\����^>	\0FEt,�}_6C�:��9̖���zy�t~O��1�����\\���4ƌN���:��r�wk�	��-f���F��\'C����;$[#���n�E͊p�����{m�ҧ�:��G���s�N6��]<;��1�u+�N{w�=f��j����a��󽬶-��p�Q6�~��]\n����u���^xa�B��ի79�:�c|\0������e��]�K�f��ْ��wIy����1+<��8/:�1��h�Esb9f����+�z\\w�����٬s������p﾿W���S�|�r���>���.�p�f�Q�.�`Z���k>���&Wg����������Y��6�}�wD����y@�=o9�}��,r>��ײ�P�SG`2jmm��X���u��ŕ�7�JH�={�x�8��J�5q(tv�q\n�����u\ZK��M���ó�:�}��e�w��\r�\\���x���;����ή@�(�����pW��;w�xvӸW���\0�S�FԬ���S<&�K�|w�QǢ��_�+���U���c�<�v�8�����Ԑ���G�y��\0}���>��3y��s�7l�0��[��$ߔ�[ly\0F��]:<�q�a_X--�i�NvXtv�����񌙗8;�p�]�&��k���Y��9������a�}���Ta�=|���M䵐�]��]n��WOia�}�*/�f�c8��{��zv�B�@_�ԨgQעv�����D@�)~>ޓ]d0.v3��=ّ0��\0�\n��a�������5k���֧�~zm@�h��)�i�\'�A/���o����G�}e�\\���7+n�T{�,T׼/:��rf�I緫xivNt��Ƭ�k���N0�AX�\'��S;�&{�m��f�{��I�.-~m`�z�]��47f���ʇ�&��o��8�d��*͉��;���lty�<�O%�?&�U���I���5S�N=0��k��Yg�Uy����{��>�u�����B2\0\r�>�Ϫ���Q���)}���P�S�:��y}����u���ow�0�k�9�ݥ���ݮ����\\�b�O�QrP5������T��;����@\\�+���5&�M���Ǜ�\n�z������u�5���c~������^*?%Bt6��jb\\k�\'�*\n{�@�x]��w�q�+����jժ����y���8f�W�XQ9����qvuk��a��۸�M~ht�|F�2�9fc�1:���s�R�0��J_\'�<c��nEGrKW����jG���׸�X�!�0\n�Yv�5��Y��Ϫ�]ޖ�Iv(\0��	�B2��1cƟ�����B_Clq���ӧO��V\0���DH=�g�~S����Ÿ84��B�psz�I�zZZ��V\0;qNr��-�\0\0\0��;l\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0&�����K3��e\0\0\0\0IEND�B`�',1);

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

insert  into `ACT_HI_ACTINST`(`ID_`,`PROC_DEF_ID_`,`PROC_INST_ID_`,`EXECUTION_ID_`,`ACT_ID_`,`TASK_ID_`,`CALL_PROC_INST_ID_`,`ACT_NAME_`,`ACT_TYPE_`,`ASSIGNEE_`,`START_TIME_`,`END_TIME_`,`DURATION_`,`TENANT_ID_`) values ('10002','leave:1:2504','7507','7507','exclusivegateway2',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 15:21:29.153','2017-04-01 15:21:29.158',5,''),('10003','leave:1:2504','7507','7507','deptleaderaudit','10004',NULL,'部门领导审批','userTask','3','2017-04-01 15:21:29.159','2017-04-01 15:22:36.235',67076,''),('10006','leave:1:2504','7507','7507','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 15:22:36.235','2017-04-01 15:22:36.235',0,''),('10007','leave:1:2504','7507','7507','modifyapply','10008',NULL,'调整申请','userTask','1','2017-04-01 15:22:36.235','2017-04-01 15:24:13.572',97337,''),('10009','leave:1:2504','7507','7507','exclusivegateway2',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 15:24:13.572','2017-04-01 15:24:13.574',2,''),('10010','leave:1:2504','7507','7507','endevent1',NULL,NULL,'End','endEvent',NULL,'2017-04-01 15:24:13.575','2017-04-01 15:24:13.576',1,''),('2508','leave:1:2504','2505','2505','startevent1',NULL,NULL,'Start','startEvent',NULL,'2017-03-31 18:08:57.430','2017-03-31 18:08:57.445',15,''),('2510','leave:1:2504','2505','2505','deptleaderaudit','2511',NULL,'部门领导审批','userTask','3','2017-03-31 18:08:57.446','2017-04-01 13:48:41.381',70783935,''),('2516','leave:1:2504','2513','2513','startevent1',NULL,NULL,'Start','startEvent',NULL,'2017-03-31 18:09:45.942','2017-03-31 18:09:45.944',2,''),('2518','leave:1:2504','2513','2513','deptleaderaudit','2519',NULL,'部门领导审批','userTask','3','2017-03-31 18:09:45.945','2017-04-01 14:53:17.312',74611367,''),('5003','leave:1:2504','2505','2505','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 13:48:41.382','2017-04-01 13:48:41.387',5,''),('5004','leave:1:2504','2505','2505','hraudit','5005',NULL,'人事审批','userTask',NULL,'2017-04-01 13:48:41.387',NULL,NULL,''),('7503','leave:1:2504','2513','2513','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 14:53:17.313','2017-04-01 14:53:17.318',5,''),('7504','leave:1:2504','2513','2513','hraudit','7505',NULL,'人事审批','userTask',NULL,'2017-04-01 14:53:17.318',NULL,NULL,''),('7510','leave:1:2504','7507','7507','startevent1',NULL,NULL,'Start','startEvent',NULL,'2017-04-01 14:55:53.935','2017-04-01 14:55:53.937',2,''),('7512','leave:1:2504','7507','7507','deptleaderaudit','7513',NULL,'部门领导审批','userTask','3','2017-04-01 14:55:53.937','2017-04-01 15:00:15.994',262057,''),('7517','leave:1:2504','7507','7507','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2017-04-01 15:00:16.009','2017-04-01 15:00:16.009',0,''),('7518','leave:1:2504','7507','7507','modifyapply','7519',NULL,'调整申请','userTask','1','2017-04-01 15:00:16.009','2017-04-01 15:21:29.152',1273143,'');

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

insert  into `ACT_HI_IDENTITYLINK`(`ID_`,`GROUP_ID_`,`TYPE_`,`USER_ID_`,`TASK_ID_`,`PROC_INST_ID_`) values ('10005','部门经理','candidate',NULL,'10004',NULL),('2507',NULL,'starter','1',NULL,'2505'),('2512','部门经理','candidate',NULL,'2511',NULL),('2515',NULL,'starter','1',NULL,'2513'),('2520','部门经理','candidate',NULL,'2519',NULL),('5001',NULL,'participant','3',NULL,'2505'),('5006','人事','candidate',NULL,'5005',NULL),('7501',NULL,'participant','3',NULL,'2513'),('7506','人事','candidate',NULL,'7505',NULL),('7509',NULL,'starter','1',NULL,'7507'),('7514','部门经理','candidate',NULL,'7513',NULL),('7515',NULL,'participant','3',NULL,'7507');

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

insert  into `ACT_HI_TASKINST`(`ID_`,`PROC_DEF_ID_`,`TASK_DEF_KEY_`,`PROC_INST_ID_`,`EXECUTION_ID_`,`NAME_`,`PARENT_TASK_ID_`,`DESCRIPTION_`,`OWNER_`,`ASSIGNEE_`,`START_TIME_`,`CLAIM_TIME_`,`END_TIME_`,`DURATION_`,`DELETE_REASON_`,`PRIORITY_`,`DUE_DATE_`,`FORM_KEY_`,`CATEGORY_`,`TENANT_ID_`) values ('10004','leave:1:2504','deptleaderaudit','7507','7507','部门领导审批',NULL,NULL,NULL,'3','2017-04-01 15:21:29.160','2017-04-01 15:22:35.734','2017-04-01 15:22:36.173',67013,'completed',50,NULL,NULL,NULL,''),('10008','leave:1:2504','modifyapply','7507','7507','调整申请',NULL,NULL,NULL,'1','2017-04-01 15:22:36.235',NULL,'2017-04-01 15:24:13.508',97273,'completed',50,NULL,NULL,NULL,''),('2511','leave:1:2504','deptleaderaudit','2505','2505','部门领导审批',NULL,NULL,NULL,'3','2017-03-31 18:08:57.447','2017-04-01 13:48:40.781','2017-04-01 13:48:41.303',70783856,'completed',50,NULL,NULL,NULL,''),('2519','leave:1:2504','deptleaderaudit','2513','2513','部门领导审批',NULL,NULL,NULL,'3','2017-03-31 18:09:45.946','2017-04-01 14:53:16.700','2017-04-01 14:53:17.234',74611288,'completed',50,NULL,NULL,NULL,''),('5005','leave:1:2504','hraudit','2505','2505','人事审批',NULL,NULL,NULL,NULL,'2017-04-01 13:48:41.388',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('7505','leave:1:2504','hraudit','2513','2513','人事审批',NULL,NULL,NULL,NULL,'2017-04-01 14:53:17.319',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('7513','leave:1:2504','deptleaderaudit','7507','7507','部门领导审批',NULL,NULL,NULL,'3','2017-04-01 14:55:53.938','2017-04-01 15:00:15.462','2017-04-01 15:00:15.947',262009,'completed',50,NULL,NULL,NULL,''),('7519','leave:1:2504','modifyapply','7507','7507','调整申请',NULL,NULL,NULL,'1','2017-04-01 15:00:16.009',NULL,'2017-04-01 15:21:29.054',1273045,'completed',50,NULL,NULL,NULL,'');

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

insert  into `ACT_RU_IDENTITYLINK`(`ID_`,`REV_`,`GROUP_ID_`,`TYPE_`,`USER_ID_`,`TASK_ID_`,`PROC_INST_ID_`,`PROC_DEF_ID_`) values ('2507',1,NULL,'starter','1',NULL,'2505',NULL),('2515',1,NULL,'starter','1',NULL,'2513',NULL),('5001',1,NULL,'participant','3',NULL,'2505',NULL),('5006',1,'人事','candidate',NULL,'5005',NULL,NULL),('7501',1,NULL,'participant','3',NULL,'2513',NULL),('7506',1,'人事','candidate',NULL,'7505',NULL,NULL);

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

insert  into `ACT_RU_TASK`(`ID_`,`REV_`,`EXECUTION_ID_`,`PROC_INST_ID_`,`PROC_DEF_ID_`,`NAME_`,`PARENT_TASK_ID_`,`DESCRIPTION_`,`TASK_DEF_KEY_`,`OWNER_`,`ASSIGNEE_`,`DELEGATION_`,`PRIORITY_`,`CREATE_TIME_`,`DUE_DATE_`,`CATEGORY_`,`SUSPENSION_STATE_`,`TENANT_ID_`,`FORM_KEY_`) values ('5005',1,'2505','2505','leave:1:2504','人事审批',NULL,NULL,'hraudit',NULL,NULL,NULL,50,'2017-04-01 13:48:41.388',NULL,NULL,1,'',NULL),('7505',1,'2513','2513','leave:1:2504','人事审批',NULL,NULL,'hraudit',NULL,NULL,NULL,50,'2017-04-01 14:53:17.319',NULL,NULL,1,'',NULL);

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
  `config_name` varchar(50) NOT NULL COMMENT '标识项目是否处于管理员调试、修改状态，用户登录之后会有相应的弹窗提醒',
  `config_value` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='全局性配置';

/*Data for the table `basic_config` */

insert  into `basic_config`(`id`,`config_name`,`config_value`) values (1,'project_debugging_tip','1');

/*Table structure for table `basic_source_download` */

DROP TABLE IF EXISTS `basic_source_download`;

CREATE TABLE `basic_source_download` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '资源名称',
  `down_url` varchar(150) NOT NULL COMMENT '下载地址',
  `icon_name` varchar(50) NOT NULL DEFAULT 'icon-download-alt' COMMENT '图标',
  `sort_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `basic_source_download` */

insert  into `basic_source_download`(`id`,`name`,`down_url`,`icon_name`,`sort_num`,`create_time`,`update_time`) values (1,'ActiveMQ消息监听处理入门Demo(Spring简单集成).zip','http://pan.baidu.com/s/1hrH9DnY','icon-download-alt',0,'2016-11-03 15:17:37','2016-11-03 15:17:39'),(2,'apache tomcat集群环境搭建图文教程.zip','http://pan.baidu.com/s/1o7DtCsq','icon-download-alt',0,'2016-11-03 15:18:34','2016-11-03 15:18:36'),(3,'chatpush-在线聊天室(可选指定用户聊天).zip','http://pan.baidu.com/s/1eR521IM','icon-download-alt',0,'2016-11-03 15:19:33','2016-11-03 15:19:35'),(4,'crm后台系统源码(bootstrap).zip','http://pan.baidu.com/s/1i4TcT29','icon-download-alt',0,'2016-11-03 15:19:56','2016-11-03 15:19:57'),(5,'DateTimePicker_JQuery日期和时间插件.zip','http://pan.baidu.com/s/1o8eyaF4','icon-download-alt',0,'2016-11-03 15:20:20','2016-11-03 15:20:22'),(6,'dwrcomet-推送消息给指定用户Demo.zip','http://pan.baidu.com/s/1kVAslEn','icon-download-alt',0,'2016-11-03 15:20:42','2016-11-03 15:20:44'),(7,'DwrPush-模拟消息推送至所有当前在线的用户.zip','http://pan.baidu.com/s/1i5BclKh','icon-download-alt',0,'2016-11-03 15:21:03','2016-11-03 15:21:05'),(8,'dwz富客户端 springmvc ibatis简单Demo.zip','http://pan.baidu.com/s/1boRTofX','icon-download-alt',0,'2016-11-03 15:21:24','2016-11-03 15:21:26'),(9,'easyui完整Demo-各种功能_布局.zip','http://pan.baidu.com/s/1nvJb8G5','icon-download-alt',0,'2016-11-03 15:21:52','2016-11-03 15:21:54'),(10,'erp项目源码--bootstrap 选项卡切换.zip','http://pan.baidu.com/s/1boLhDLl','icon-download-alt',0,'2016-11-03 15:22:13','2016-11-03 15:22:15'),(11,'Highcharts简单实例Demo.zip','http://pan.baidu.com/s/1dFt91nF','icon-download-alt',0,'2016-11-03 15:22:37','2016-11-03 15:22:38'),(12,'HTML5 CSS3鼠标悬停图片特效.zip','http://pan.baidu.com/s/1eSLXIee','icon-download-alt',0,'2016-11-03 15:23:01','2016-11-03 15:23:03'),(13,'Java web项目：mybatis、springmvc、seajs、freemarker、分布式session、自定义taglib等.zip','http://pan.baidu.com/s/1qYzsFxU','icon-download-alt',0,'2016-11-03 15:23:21','2016-11-03 15:23:23'),(14,'Java后台系统-不同框架优点集成-bootstrap布局-权限角色管理(含sql)----1.0.0版本.zip','http://pan.baidu.com/s/1i4GlFql','icon-download-alt',0,'2016-11-03 15:23:43','2016-11-03 15:23:44'),(15,'java线程异步：异步发送email、回调接口定义、其他异步线程demo.zip','http://pan.baidu.com/s/1jIbOunO','icon-download-alt',0,'2016-11-03 15:24:06','2016-11-03 15:24:08'),(16,'JAVA之23种设计模式的完整实例代码.zip','http://pan.baidu.com/s/1o8NJsL0','icon-download-alt',0,'2016-11-03 15:24:28','2016-11-03 15:24:29'),(17,'jd-gui反编译jar_复制源码为java文件(自己写的工具).rar','http://pan.baidu.com/s/1dE8mrap','icon-download-alt',0,'2016-11-03 15:24:47','2016-11-03 15:24:50'),(18,'jquery点击图片选中特效.rar','http://pan.baidu.com/s/1jIJNEq6','icon-download-alt',0,'2016-11-03 15:25:12','2016-11-03 15:25:13'),(19,'jQuery轻量级圆形进度指示器插件.zip','http://pan.baidu.com/s/1o8Lbz4m','icon-download-alt',0,'2016-11-03 15:27:51','2016-11-03 15:27:53'),(20,'lucene基于本地磁盘的文件索引Demo.zip','http://pan.baidu.com/s/1jI8suqy','icon-download-alt',0,'2016-11-03 15:28:11','2016-11-03 15:28:13'),(21,'lucene基于内存的检索Demo.zip','http://pan.baidu.com/s/1qXEB2DA','icon-download-alt',0,'2016-11-03 15:28:31','2016-11-03 15:28:32'),(22,'lucene索引——增删改查.zip','http://pan.baidu.com/s/1bo2kKCf','icon-download-alt',0,'2016-11-03 15:28:49','2016-11-03 15:28:50'),(23,'MongoDB环境搭建与实例代码测试.rar','http://pan.baidu.com/s/1o7OiXMM','icon-download-alt',0,'2016-11-03 15:29:09','2016-11-03 15:29:10'),(24,'orm框架 微信框架 自定义拦截器 自定义注解 数据源切换等.zip','http://pan.baidu.com/s/1jHMVoiU','icon-download-alt',0,'2016-11-03 15:29:29','2016-11-03 15:29:31'),(25,'pdf文件打印(freemarker作为pdf模板，通过参数替换、遍历显示效果)（java版 spring）.zip','http://pan.baidu.com/s/1kUWw8Vd','icon-download-alt',0,'2016-11-03 15:29:47','2016-11-03 15:29:49'),(26,'pluplaod文件分割上传Demo.zip','http://pan.baidu.com/s/1kV8Wpy3','icon-download-alt',0,'2016-11-03 15:30:05','2016-11-03 15:30:07'),(27,'SpringActiveMQ消息发送与接收(WEB版)-入门实例.zip','http://pan.baidu.com/s/1hsqL6vE','icon-download-alt',0,'2016-11-03 15:30:24','2016-11-03 15:30:26'),(28,'springbatch批处理(完整Demo).zip','http://pan.baidu.com/s/1hsgCgVq','icon-download-alt',0,'2016-11-03 15:30:47','2016-11-03 15:30:49'),(29,'springmvc mybatis集成—干净框架(直接使用).zip','http://pan.baidu.com/s/1i4XgFjR','icon-download-alt',0,'2016-11-03 15:31:09','2016-11-03 15:31:10'),(30,'springsecurity权限控制Demo.zip','http://pan.baidu.com/s/1i4MXsnr','icon-download-alt',0,'2016-11-03 15:31:30','2016-11-03 15:31:31'),(31,'spring_activemq消息发送接收(最终版-非WEB访问).zip','http://pan.baidu.com/s/1jIDcfII','icon-download-alt',0,'2016-11-03 15:31:47','2016-11-03 15:31:49'),(32,'spring_security3_详细配置(摘自网络).zip','http://pan.baidu.com/s/1kUAbWjl','icon-download-alt',0,'2016-11-03 15:32:09','2016-11-03 15:32:10'),(33,'SSO之CAS单点登录详细图文教程.zip','http://pan.baidu.com/s/1hrY4BBm','icon-download-alt',0,'2016-11-03 15:32:29','2016-11-03 15:32:30'),(34,'WebSocket消息聊天室Demo.zip','http://pan.baidu.com/s/1jI7CzAY','icon-download-alt',0,'2016-11-03 15:32:51','2016-11-03 15:32:52'),(35,'读取Excel工具类(包含流读取、文件读取).zip','http://pan.baidu.com/s/1skE92ET','icon-download-alt',0,'2016-11-03 15:33:09','2016-11-03 15:33:11'),(36,'断点 多线程下载实例.zip','http://pan.baidu.com/s/1miuHeIG','icon-download-alt',0,'2016-11-03 15:33:31','2016-11-03 15:33:32'),(37,'基于dwr消息推送Demo.zip','http://pan.baidu.com/s/1boRTogJ','icon-download-alt',0,'2016-11-03 15:33:47','2016-11-03 15:33:48'),(38,'手机_日期时间控件.zip','http://pan.baidu.com/s/1hs7G2HY','icon-download-alt',0,'2016-11-03 15:34:05','2016-11-03 15:34:07'),(39,'手机时间控件.zip','http://pan.baidu.com/s/1qXYou7M','icon-download-alt',0,'2016-11-03 15:34:25','2016-11-03 15:34:27'),(40,'数据采集—基于百度文库的抓取Demo.zip','http://pan.baidu.com/s/1cpBMeM','icon-download-alt',0,'2016-11-03 15:34:46','2016-11-03 15:34:47'),(41,'网页加载进度条-实例Demo.zip','http://pan.baidu.com/s/1pLViL9x','icon-download-alt',0,'2016-11-03 15:35:07','2016-11-03 15:35:08'),(42,'微信JSAPI支付完整Demo.zip','http://pan.baidu.com/s/1nvdehnj','icon-download-alt',0,'2016-11-03 15:35:29','2016-11-03 15:35:30'),(43,'微信JSAPI支付完整Demo第二版(新增关闭订单、查询订单、查询退款、下载对账单、申请退款).zip','http://pan.baidu.com/s/1i4VKKNN','icon-download-alt',0,'2016-11-03 15:35:47','2016-11-03 15:35:48'),(44,'异步发送email之demo.zip','http://pan.baidu.com/s/1o7IJv1S','icon-download-alt',0,'2016-11-03 15:36:05','2016-11-03 15:36:07'),(45,'银联PC网关支付demo(退款、对账文件、查询、撤销).zip','http://pan.baidu.com/s/1pKXAz0Z','icon-download-alt',0,'2016-11-03 15:36:25','2016-11-03 15:36:27'),(46,'支付宝PC及时到账支付Demo-JAVA.zip','http://pan.baidu.com/s/1pLyEKsz','icon-download-alt',0,'2016-11-03 15:36:44','2016-11-03 15:36:46'),(47,'支付宝PC支付完整Demo.zip','http://pan.baidu.com/s/1kUAbWjH','icon-download-alt',0,'2016-11-03 15:37:03','2016-11-03 15:37:05');

/*Table structure for table `leave_apply` */

DROP TABLE IF EXISTS `leave_apply`;

CREATE TABLE `leave_apply` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `process_instance_id` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `type` int(1) NOT NULL COMMENT '请假类型',
  `reason` varchar(100) NOT NULL COMMENT '请假事由',
  `user_id` bigint(11) NOT NULL COMMENT '申请人id',
  `start_date` datetime NOT NULL COMMENT '申请日期',
  `end_date` datetime NOT NULL COMMENT '结束日期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='OA请假申请表';

/*Data for the table `leave_apply` */

insert  into `leave_apply`(`id`,`process_instance_id`,`type`,`reason`,`user_id`,`start_date`,`end_date`,`create_time`,`update_time`) values (9,'2505',0,'身体不舒服了',1,'2017-03-31 00:00:00','2017-04-01 00:00:00','2017-03-31 18:08:57','2017-03-31 18:08:57'),(10,'2513',1,'回去见女朋友哈',1,'2017-03-29 00:00:00','2017-03-31 00:00:00','2017-03-31 18:09:46','2017-03-31 18:09:46'),(11,'7507',4,'生孩子去~哈哈',1,'2017-04-01 00:00:00','2017-04-07 00:00:00','2017-04-01 14:55:54','2017-04-01 14:55:54');

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
  `permission_code` varchar(50) NOT NULL COMMENT '所需权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`parent_id`,`sort`,`icon`,`type`,`permission_code`) values (1,'系统管理','#',0,'1','icon-cog','1','sys:mgt'),(2,'用户管理','/user/toUserListView',1,'2',NULL,'1','user:mgt'),(4,'角色管理','/role/toRoleListView',1,'4',NULL,'1','role:mgt'),(5,'权限管理','/permission/toListView',1,'3',NULL,'1','permission:mgt'),(6,'微信开发','#',0,'1','icon-comments-alt','1','wechat:mgt'),(7,'模板消息','/wechat/template/toTemplateMsgView',6,'2',NULL,'1','template:msg:send'),(9,'实时监控','#',0,'1','icon-laptop','1','monitor:mgt'),(10,'tomcat日志实时监控','/socket/tomcatlog',9,'2',NULL,'1','monitor:tomcat:log'),(11,'消息推送','#',0,'1','icon-rss','1','socket:push:mgt'),(12,'在线聊天','#',0,'1','icon-headphones','1','chat:mgt'),(13,'netty实现在线聊天','/chat/toChatView',12,'2','icon-rss','1','chat:netty:service'),(14,'activiti工作流','#',0,'1','icon-stethoscope','1','activiti:mgt'),(15,'OA请假申请','/oa/toOaApplyView',14,'3','icon-rss','1','activiti:oa:apply'),(16,'已部署的工作流','/activiti/toProcessListView',14,'2','icon-rss','1','activiti:process:list'),(17,'我发起的请假流程','/oa/toMyLeaveApplyView',14,'4','icon-rss','1','activiti:myprocess:leaveapply'),(18,'部门领导审批','/oa/toDeptleaderAuditView',14,'5','icon-rss','1','activiti:deptleader:audit'),(19,'请假调整申请','/oa/toMyLeaveApplyTurndownView',14,'6','icon-rss','1','activiti:myleaveapply:modify'),(20,'我的请假历史','/oa/toMyLeaveApplyHistoryView',14,'7','icon-rss','1','activiti:myleaveapplyed:history');

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '权限名称',
  `code` varchar(50) NOT NULL COMMENT '权限代码',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态，0：可用，1：不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`name`,`code`,`description`,`parent_id`,`status`) values (1,'系统管理','sys:mgt','对系统模块的管理',-1,0),(2,'人员管理','user:mgt','对人员进行管理',1,0),(3,'角色管理','role:mgt','对角色进行管理',1,0),(4,'权限管理','permission:mgt','对权限进行管理',1,0),(9,'查看用户列表','user:list','可以查看用户列表',2,0),(10,'编辑用户信息','user:edit','可以编辑用户信息',9,0),(11,'删除用户','user:delete','可以删除用户',9,0),(12,'查看角色列表','role:list','可以查看角色列表',3,0),(13,'查看权限列表','permission:list',NULL,4,0),(14,'新增角色','role:add','新增角色信息',12,0),(15,'编辑角色','role:edit','编辑角色信息',12,0),(16,'删除角色','role:delete','可以删除角色',12,0),(17,'新增权限','permission:add','可以添加新权限',13,0),(18,'编辑权限','permission:edit','修改权限信息',13,0),(19,'删除权限','permission:delete','可以删除权限数据',13,0),(20,'添加用户','user:add','可以添加新的用户',9,0),(21,'微信开发','wechat:mgt','微信相关功能开发演示',-1,0),(22,'微信模板消息','template:msg:send','测试发送微信模板消息',21,0),(23,'监控管理','monitor:mgt','监控管理',-1,0),(24,'tomcat日志监控','monitor:tomcat:log','可以查看服务器上tomcat的实时打印日志',23,0),(25,'服务器消息推送','socket:push:mgt','服务器消息推送',-1,0),(26,'在线聊天','chat:mgt','在线聊天',-1,0),(27,'netty实现在线聊天','chat:netty:service','netty实现在线聊天',26,0),(28,'activiti工作流','activiti:mgt','activiti工作流相关技术',-1,0),(29,'OA请假流程','activiti:oa:apply','基于工作流实现OA请假',28,0),(30,'已部署的工作流','activiti:process:list','已部署的工作流',28,0),(31,'我发起的请假流程','activiti:myprocess:leaveapply','我发起的请假流程',28,0),(32,'部门领导审批','activiti:deptleader:audit','部门领导审批',28,0),(33,'请假调整申请','activiti:myleaveapply:modify','重新修改被驳回的请假申请',28,0),(34,'我的请假历史','activiti:myleaveapplyed:history','我的请假历史',28,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(24) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`description`,`status`,`update_time`,`create_time`) values (1,'超级管理员','最高级别权限管理员',0,'2016-08-01 15:17:52','2016-08-01 15:17:53'),(2,'员工','基层员工',0,'2016-08-01 15:18:21','2016-08-01 15:18:23'),(3,'财务','财务部角色',0,'2016-08-03 13:21:56','2016-08-03 13:21:58'),(4,'人事','人事',0,'2017-03-21 13:19:34','2017-03-21 13:19:34'),(5,'部门经理','部门经理',0,'2017-03-21 13:20:47','2017-03-21 13:20:47'),(6,'出纳员','出纳员',0,'2017-03-21 13:22:06','2017-03-21 13:22:06'),(7,'采购经理','采购经理',0,'2017-03-21 13:22:23','2017-03-21 13:22:23'),(8,'总经理','总经理',0,'2017-03-21 13:23:03','2017-03-21 13:23:03');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=758 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`id`,`role_id`,`permission_id`) values (226,3,-1),(227,3,1),(228,3,2),(688,5,-1),(689,5,28),(690,5,30),(691,5,32),(720,1,-1),(721,1,1),(722,1,2),(723,1,9),(724,1,10),(725,1,11),(726,1,20),(727,1,3),(728,1,12),(729,1,14),(730,1,15),(731,1,16),(732,1,4),(733,1,13),(734,1,17),(735,1,18),(736,1,19),(737,1,21),(738,1,22),(739,1,23),(740,1,24),(741,1,26),(742,1,27),(743,1,28),(744,1,29),(745,1,30),(746,1,31),(747,1,33),(748,1,34),(749,2,-1),(750,2,1),(751,2,2),(752,2,28),(753,2,29),(754,2,30),(755,2,31),(756,2,33),(757,2,34);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL COMMENT '登录用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '账号状态',
  `open_id` varchar(50) DEFAULT NULL COMMENT '微信openid',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`password`,`real_name`,`status`,`open_id`,`create_time`,`update_time`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e','超级管理员',0,NULL,'2016-08-03 13:22:46','2017-03-31 18:16:39'),(2,'yuangong01','e10adc3949ba59abbe56e057f20f883e','员工一',0,NULL,'2017-03-31 18:13:27','2017-03-31 18:13:27'),(3,'bumenjingli','e10adc3949ba59abbe56e057f20f883e','部门经理',0,NULL,'2017-04-01 11:36:59','2017-04-01 11:36:59');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (66,1,1),(67,2,2),(68,3,5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
