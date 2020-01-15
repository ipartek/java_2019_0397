<html>
<head>
<title>Hola</title>
</head>
<body>

	<%
		for (int i = 1; i <= 6; i++) {
	%>
	<h<%=i%>>Ejemplo H<%=i%></h<%=i%>>
	<%
		}
	%>

	<table>
		<tbody>
			<%
				for (int n = 1; n <= 10; n++) {
			%>
			<tr>
				<td>5</td>
				<td>x</td>
				<td><%=n%></td>
				<td>=</td>
				<td><%=5 * n%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

</body>
</html>
