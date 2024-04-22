function acessarPaginaPrincipal() {
  const token = localStorage.getItem("token");
  console.log(token)
  if (token) {
    const headers = {
      Authorization: "Bearer " + token,
    };
    console.log(headers);

    axios.get("/auth/main", { headers: headers })
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  } else {
    console.error(
      "Token de autenticação não encontrado no armazenamento local."
    );
  }
}

acessarPaginaPrincipal();