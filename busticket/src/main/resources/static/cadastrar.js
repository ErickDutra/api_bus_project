document.getElementById("registroForm").addEventListener("submit", function(event) {
    event.preventDefault(); 

    const formData = new FormData(this); 
    const registerEndpoint = "/register";

    fetch(registerEndpoint, {
        method: "POST",
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao registrar usuário");
        }
        return response.json();
    })
    .then(data => {
        console.log("Usuário registrado com sucesso:",data);
    
    })
    .catch(error => {
        console.error("Erro:", error);
       
    });
});
