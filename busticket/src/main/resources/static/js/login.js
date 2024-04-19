document.getElementById('form').addEventListener('submit', function(event) {
    event.preventDefault();

    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    var data = {
        "email": email,
        "password": password
    };

    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    }).then(function(response) {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Erro ao acessar');
        }
    }).then(function(data) {
       
        localStorage.setItem('token', data.token);

        var authToken = localStorage.getItem('token');
        if (authToken) {
            var header = {
                'Authorization': 'Bearer ' + authToken
            };

            console.log(header);
            fetch('/auth/main', {
                method: 'GET',
                headers: header
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao acessar /auth/main');
                }
                return response.json(); // Adicione esta linha
            })
            .then(data => {
                var token = data.token; // O token está agora disponível aqui
                console.log(token);
                window.location.href = '/auth/main';
            })
            .catch(error => {
                console.error('Erro ao acessar /auth/main:', error);
            });
        } else {
            console.error('Token de autenticação não encontrado.');
        }
        
    }).catch(function(error) {
        console.error(error);
    });
});
