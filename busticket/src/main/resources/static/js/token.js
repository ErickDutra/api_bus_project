function acessarPaginaPrincipal() {
  const token = localStorage.getItem("authToken");

  if (token) {
    const headers = {
      Authorization: `Bearer ${token}`,
    };

    fetch("/auth/main", {
      method: "GET",
      headers: headers,
    })
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error("Erro ao acessar a página principal");
        }
      })
      .then((data) => {
        console.log("Dados da página principal:", data);
      })
      .catch((error) => {
        console.error("Erro ao acessar a página principal:", error);
      });
  } else {
    console.error(
      "Token de autenticação não encontrado no armazenamento local."
    );
  }
}

acessarPaginaPrincipal();
