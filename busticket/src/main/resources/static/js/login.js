document.getElementById('form').addEventListener('submit', function(event) {
    event.preventDefault();

    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    var data = {
        "email": email,
        "password": password
    };

    axios.post('/auth/login', data)
    .then(function(response) {
        localStorage.setItem('token', response.data.token);

        var authToken = localStorage.getItem('token');
        if (authToken) {
            var header = {
                'Authorization': 'Bearer ' + authToken
            };

            console.log(header);
            axios.get('/auth/main', { headers: header })
            .then(response => {
                var token = response.data.token; 
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