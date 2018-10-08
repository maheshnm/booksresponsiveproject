<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/header-style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/accessdb.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jspdf.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jspdf.debug.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jspdf.plugin.from_html.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jspdf.plugin.split_text_to_size.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jspdf.plugin.standard_fonts_metrics.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

</head>
<script type="text/javascript">
	function openlib(evt, searchName) {
		var i,
			tabcontent,
			tablinks;
		tabcontent = document.getElementsByClassName("tabcontent");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}
		tablinks = document.getElementsByClassName("tablinks");
		for (i = 0; i < tablinks.length; i++) {
			tablinks[i].className = tablinks[i].className.replace(" active", "");
		}
		document.getElementById(searchName).style.display = "block";
		evt.currentTarget.className += " active";
	}
	
	$(document).ready(function() {
		$("#searchsection").click();
		$("tr:odd").css({
			"background-color" : "#e0e0e0",
		});
	});

	$(document).ready(function() {
		$(function() {
			$("#name").autocomplete({
				source : function(request, response) {
				$('#ajax_loader').show();
					$.ajax({
						url : "SearchController",
						type : "GET",
						data : {
							term : request.term
						},
						dataType : "json",
						success : function(data) {
							response(data);
						},
						complete : function() {
						$('#ajax_loader').hide();
					}
					});
				}
			});
		});
	});
</script>
<body>
	<div id="header" align="center">
		<div id="headerText">
			<b><h1 class="headerTitle">Open Library Utility</h1></b>
		</div>
	</div>

	<div class="tab">
		<button id="searchid" class="tablinks"
			onclick="openlib(event, 'searchsection')">
			<b>Search For Books</b>
		</button>
	</div>
	<div id="searchsection" class="tabcontent">
		<h4>Books Inventory</h4>
		<form:form method="post" action="booksresponsiveproject">
			<table style="border:none; text-align:center;" id="reserve">
				<tr>
					<td style="text-align:right;"><label>Name of the book:</label></td>
					<td style="text-align:left;padding-left:5em;"><input
						type="text" name="name" id="name" size="40" value=""
						required /></td>
				</tr>
			</table>
			<input id="submitres" align="right" type="submit" value="Get Details"
				class="buttonHolder" />
		</form:form>
	</div>

	<div id='ajax_loader'
		style="position: fixed; left: 40%; top: 20%; display: none;">
		<img src="${pageContext.request.contextPath}/resources/imgs/load.gif">
	</div>
	<div>	
		<c:if test="${fn:length(EnvList)>0}">
			<table align="center">
				<tr align="center">
					<td><b>Library Inventroy Details:</b></td>
				</tr>
			</table>
			<table id="envstatustable" width="80%" border="1" align="center">
							
				<tr>
					<th>Book Name</th>
					<th>Author Details</th>
					<th>Softcopy Available</th>
				</tr>
				<tbody>
					<c:forEach items="${EnvList}" var="temp">
						<tr>
							<td>&nbsp;${temp.name}</td>
							<td>&nbsp;${temp.authorDetails}</td>
							<td>&nbsp;${temp.printDisabled}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>
