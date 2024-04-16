document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var formData = new FormData(event.target);
    var data = {};

    formData.forEach(function(value, key) {
        data[key] = value;
    });

    fetch('/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(function(response) {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('Erro ao registrar');
           
        }
    }).then(function(text) {
         window.location.href = '/auth/main';
        console.log(text);
    }).catch(function(error) {
        console.error(error);
    });
});