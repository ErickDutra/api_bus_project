function acessarPaginaPrincipal() {
  const token = localStorage.getItem("token");
  console.log(token)
  if (token) {
    fetch("/auth/main", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
      
    },
    console.log(headers)
  )
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
      })
      .catch((error) => {
        console.error("Error:", error);
      });
      console.log(headers)
  } else {
    console.error(
      "Token de autenticação não encontrado no armazenamento local."
    );
  }
}

acessarPaginaPrincipal();
