// quando o documento terminar de carregar
$(function() { // onLoad

	var url = "http://localhost:4567/contatos";
	// ajax que busca os dados desta url
	// os dados recebidos (data) e passa para
	// uma função
	/*
	[{"id":19,"nome":"Betito","sobrenome":"Bira","telefones":[]},{"id":18,"nome":"Ogro","sobrenome":"Betito","telefones":[]}]
	*/
	$.get(url, mostra);

});

function mostra(data) {
	// for (var i = 0; i < data.length; i++)
	console.dir(data[0].nome);
	var li = document.createElement('li');
	li.className = 'collection-item';
	li.innerText = data[0].nome;
	console.log(li);
	var ul = document.querySelector('ul');
	ul.appendChild(li);
}





