<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr"%>

<h4>
	<bean:message bundle="JOB_BANK_RESOURCES" key="link.sideBar.jobBank.studentAuthorizations"/>
	<span class="processNumber">(<bean:write name="process" property="processIdentification"/>)</span>	
</h4>

