$(function() {
  var contatos;

  $.get('http://localhost:4567/contatos', function(data, status) {
    contatos = data;
    alert(contatos);
  });
});
