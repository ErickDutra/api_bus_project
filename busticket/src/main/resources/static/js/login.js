document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    var data = {
        email: email,
        password: password
    };
    formData.forEach(function(value, key) {
        data[key] = value;
    });

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
        // Armazenar o token de autenticação
        localStorage.setItem('authToken', data.token);
        localStorage.setItem('userEmail', data.email);
        localStorage.setItem('userName', data.name);
        localStorage.setItem('userCpf', data.Cpf);

        // Adicionar o token ao cabeçalho ao navegar para /auth/main
        var authToken = localStorage.getItem('authToken');
        if (authToken) {
            // Redirecionar para /auth/main com o token no cabeçalho
            var headers = {
                'Authorization': 'Bearer ' + authToken
            };

            fetch('/auth/main', {
                method: 'GET',
                headers: headers
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                if (data) {
                    return JSON.parse(data);
                } else {
                    return {};
                }
            })
            .then(function(response) {
                // Verificar se a resposta foi bem-sucedida
                if (response.ok) {
                    // Redirecionar para a página principal após o login
                    window.location.href = '/auth/main';
                } else {
                    // Se a resposta não foi bem-sucedida, lidar com isso de acordo com sua lógica de aplicativo
                    console.error('Erro ao acessar /auth/main');
                }
            }).catch(function(error) {
                console.error('Erro ao acessar /auth/main:', error);
            });
        } else {
            console.error('Token de autenticação não encontrado.');
        }
        
    }).catch(function(error) {
        console.error(error);
    });
});
