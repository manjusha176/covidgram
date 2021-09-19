<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ page trimDirectiveWhitespaces="true" %><%@ page contentType="application/voicexml+xml; charset=UTF-8" %><%response.setHeader("Cache-Control", "no-cache");response.setHeader("Pragma", "no-cache");response.setHeader("Expires", "0");%>
<%
  String appName = (String)request.getContextPath();
%>
<?xml version="1.0" encoding="UTF-8"?>
<vxml version="2.0" xmlns="http://www.w3.org/2001/vxml">
  <!--script src="/js/util.js"/-->
	<form>
		<block>
			<prompt>Error on CovidGram. Please contact Administrators </prompt>
		</block>
	</form>
</vxml>