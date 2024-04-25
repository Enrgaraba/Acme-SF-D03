<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>


<acme:list>
	<acme:list-column code="any.auditRecord.list.label.code" path="code"/>
	<acme:list-column code="any.auditRecord.list.label.startDate" path="startDate"/>
	<acme:list-column code="any.auditRecord.list.label.endDate" path="endDate"/>
	<acme:list-column code="any.auditRecord.list.label.mark" path="mark"/>
</acme:list>