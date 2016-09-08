$(function() { // onLoad

	var url = "http://localhost:4567/contatos";
	// ajax feito pelo jQuery
	$.get(url, mostra);

});
// data == dados
function mostra(contatos) {
	for (var i = 0; i < contatos.length; i++) {
		var str = "<li class='collection-item' onclick='mostraContato(" + contatos[i].id + ")'>" + contatos[i].nome + "</li>";
		// console.log(str);
		$('ul').append(str);
	}
}

function mostraContato(id) {
	console.log('funciona! ' + id);
	var url = "http://localhost:4567/contatos/" + id;
	$.get(url, function(contato){
		$('#modal1 h4').text(contato.nome);	
		$("#modal1 p").empty();
		for (var i = 0; i < contato.telefones.length; i++) {
			$("#modal1 p").append(
				"<a style='display:block' href='tel:" 
					+ contato.telefones[i].ddd 
					+ contato.telefones[i].numero 
					+ "'>(" + contato.telefones[i].ddd + ") " 
					+ contato.telefones[i].numero + "</a>"
			);
		}	
		$('#modal1').openModal();
	});
}



















