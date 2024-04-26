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
        const authToken = response.data.token;
        localStorage.setItem('token', authToken);

        // Adicionando o cabeçalho de autorização na requisição para '/auth/main'
        axios.get('/auth/main', {
            headers: {
                'Authorization':authToken
            }
        })
        .then(function(response) {
            // handle success
            window.location.href = '/auth/main';
        })
        .catch(function(error) {
            // handle error
            console.error(error);
        });

    }).catch(function(error) {
        console.error(error);
    });
});