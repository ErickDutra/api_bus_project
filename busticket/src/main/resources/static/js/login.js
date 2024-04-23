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
        window.location.href = '/auth/main';
    }).catch(function(error) {
        console.error(error);
    });
});