<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pessoas</title>
</head>
<body>
	<div align="center">
		<jsp:include page="menu.jsp"></jsp:include>
	</div>
	<br />
	<br />
	<div align="center">
		<form action="pessoa" method="post">
			<table border="1">
				<tr>
					<td colspan="3">
						<input type="number" min="0" step="1" id="idPessoa" name="idPessoa" placeholder="#ID"
						value='<c:out value="${pessoa.id }"></c:out>'>
					</td>
					<td><input type="submit" id="botao" name="botao" value="CONSULTAR"></td>
				<tr>
					<td colspan="4">
						<input type="text" id="nomePessoa" name="nomePessoa" placeholder="Nome"
						value='<c:out value="${pessoa.nome }"></c:out>'>
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" id="emailPessoa" name="telefoneFixoPessoa" placeholder="Telefone Fixo"
						value='<c:out value="${pessoa.telefoneFixo }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" id="telefonePessoa" name="telefoneCelularPessoa" placeholder="Telefone Celular"
						value='<c:out value="${pessoa.telefoneCelular }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td><input type="submit" id="botao" name="botao" value="INSERIR"></td>
					<td><input type="submit" id="botao" name="botao" value="ATUALIZAR"></td>
					<td><input type="submit" id="botao" name="botao" value="EXCLUIR"></td>
					<td><input type="submit" id="botao" name="botao" value="LISTAR"></td>
				</tr>
			</table>
		</form>
	</div>
	<br />
	<div align="center">
		<c:if test="${not empty erro }">
			<H2><c:out value="${erro }" /></H2>
		</c:if>
		<c:if test="${not empty saida }">
			<H2><c:out value="${saida }" /></H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty pessoas }">
			<table border="1">
				<thead>
					<tr>
						<th>#ID Pessoa</th>
						<th>Nome Pessoa</th>
						<th>Telefone Fixo Pessoa</th>
						<th>Telefone Celular Pessoa</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pessoas }" var="p">
						<tr>
							<td><c:out value="${p.id }"></c:out></td>
							<td><c:out value="${p.nome }"></c:out></td>
							<td><c:out value="${p.telefoneFixo }"></c:out></td>
							<td><c:out value="${p.telefoneCelular }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	</div>
</body>
</html>