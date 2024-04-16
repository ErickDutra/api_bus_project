document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var formData = new FormData(event.target);
    var data = {};

    formData.forEach(function(value, key) {
        data[key] = value;
    });

    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(function(response) {
        if (response.ok) {
            return response.json(); // Alterado para json() para extrair o token do corpo da resposta
        } else {
            throw new Error('Erro ao acessar');
        }
    }).then(function(data) {
        // Armazenar o token de autenticação
        localStorage.setItem('authToken', data.token);

        // Redirecionar para /main
        window.location.href = '/auth/main';
    }).catch(function(error) {
        console.error(error);
    });
});