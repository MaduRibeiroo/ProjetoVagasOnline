<template>
  <header class="header">
    <a href="#" class="logo"
      ><router-link style="color: white" to="/">VAGAS</router-link></a
    >

    <nav class="navbarra">
      <a style="--i: 2" @click="irparaformVagas">CRIAR<router-view></router-view></a>
    </nav>
  </header>
  <h1 class="sub-title" style="margin-top: 100px"><span>Sobre as vagas</span></h1>
  <div style="width: 80%; margin-left: auto; margin-right: auto; margin-bottom: 200px">
    <div v-if="formOn">
      <main class="main-content">
        <div class="formContent">
          <h3 class="titulo-principal">VAGAS</h3>
          <p class="titulo-instrucao">Busque a vaga pelo cargo</p>
          <form class="form-pesquisa" @submit.prevent="buscarTitulo">
            <input
              type="text"
              id="buscar"
              placeholder="Digite o cargo..."
              v-model="cargo"
            />
            <button id="btn-box" class="btn-box" style="color: white" type="submit">Pesquisar</button>
            <p v-if="mensagem" style="color: #ff9999; margin-top: 20px">{{ mensagem }}</p>
          </form>
        </div>
        <div class="divAnuncios">
          <div v-for="vag in this.vagas" :key="vag.registro" class="cards">
            <h3>{{ vag.empresa.nome_fantasia }}</h3>
            <h4>{{ vag.empresa.razao_social }}</h4>
            <h4>{{ vag.empresa.tipo }}</h4>
            <h5>{{ vag.cargo }}</h5>
            <h5>{{ vag.cidade }}</h5>
            <h5>{{ vag.estado }}</h5>
            <h5>{{ vag.pre_requisitos }}</h5>
            <h5>{{ vag.formacao }}</h5>
            <h5>{{ vag.conhecimentos_requeridos }}</h5>
            <h5>{{ vag.regime }}</h5>
            <h5>{{ vag.jornada_trabalho }}</h5>
            <h5 style=" margin-bottom: 20px;">{{ vag.remuneracao }}</h5>
            <button class="btn-box" style="color: white;" @click.stop="excluirVagas(vag)">
              Excluir vaga
            </button>
          </div>
        </div>
      </main>
    </div>
    <!--<div style="display: flex; justify-content: flex-end;">
      <button class="btn-box" style="color: white;" @click="this.mostrarForm(true)">Nova Vaga</button>
    </div>-->
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "BuscarVagas",

  data() {
    return {
      registro: "",
      nome_fantasia: "",
      razao_social: "",
      tipo: "",

      cargo: "",
      cidade: "",
      estado: "",
      pre_requisitos: "",
      formacao: "",
      conhecimentos_requeridos: "",
      regime: "",
      jornada_trabalho: "",
      remuneracao: "",

      vagas: [],
      mensagem: "",
      formOn: true,
    };
  },

  mounted() {
    this.carregarDados();
  },

  methods: {
    irparaformVagas() {
      this.$router.push({ name: "formVagas" });
    },

    // -----------------------------
    // GET ALL VAGAS
    // -----------------------------
    carregarDados() {
      axios
        .get("http://localhost:8080/apis/vagas/get-all")
        .then((result) => {
          this.vagas = result.data;
          this.mensagem = "";
        })
        .catch(() => {
          this.mensagem = "Erro ao carregar vagas!";
        });
    },

    // -----------------------------
    // FILTRAR POR CARGO (FRONT-END)
    // -----------------------------
    buscarTitulo() {
      axios
        .get("http://localhost:8080/apis/vagas/get-all")
        .then((result) => {
          const lista = result.data;

          this.vagas = lista.filter((v) =>
            v.cargo.toLowerCase().includes(this.cargo.toLowerCase())
          );

          if (this.vagas.length === 0) {
            this.mensagem = "Nenhuma vaga encontrada!";
          } else {
            this.mensagem = "";
          }
        })
        .catch(() => {
          this.mensagem = "Erro ao buscar!";
        });
    },

    // -----------------------------
    // VER DETALHES DA VAGA
    // -----------------------------
    irParaVagaIndividual(vaga) {
      this.$router.push({
        name: "VagaIndividual",
        params: { registro: vaga.registro },
      });
    },

    // -----------------------------
    // EXCLUIR VAGA
    // -----------------------------
    excluirVagas(vaga) {
      if (confirm("Deseja realmente excluir a vaga: " + vaga.cargo + "?")) {
        axios
          .delete("http://localhost:8080/apis/vagas/" + vaga.registro)
          .then(() => {
            this.mensagem = "Vaga excluída com sucesso!";
            this.carregarDados();
          })
          .catch((error) => {
            alert("Erro ao excluir: " + "vaga com interesse");
          });
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
input[type="text"],
select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type="submit"] {
  width: 100%;
  background-color: #4caf50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type="submit"]:hover {
  background-color: #45a049;
}

div {
  border-radius: 30px;
  background-color: #015287f7;
  padding: 20px;
}

.btn-box {
  display: inline-block;
  padding: 12px 28px;
  background-color: #103482f7;
  border-radius: 40px;
  font-size: 16px;
  color: #000;
  letter-spacing: 1px;
  text-decoration: none;
  font-weight: 600;
  opacity: 0;
  animation: slideTop 1s ease forwards;
  animation-delay: 2s;  
  box-shadow: 0 0 5px #53bafff7, 0 0 25px #53bafff7;
}

.main-content {
  text-align: center;
  padding: 60px 20px;
  margin-top: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.titulo-principal {
  font-size: 28px;
  font-weight: bold;
  margin: 10px 0;
}

.texto-instrucao {
  font-size: 18px;
  margin-bottom: 20px;
}

.form-pesquisa {
  margin-top: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  gap: 12px;
  width: 100%;
}

.form-pesquisa input {
  padding: 14px 20px;
  font-size: 18px;
  border-radius: 10px;
  border: 1px solid #ccc;
  width: 400px;
  max-width: 90%;
  background-color: #fff;
  color: #000;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
}

.form-pesquisa button {
  padding: 12px 20px;
  font-size: 16px;
  border-radius: 6px;
  border: none;
  background-color: #53baff;
  color: white;
  cursor: pointer;
  transition: 0.3s ease;
}

.form-pesquisa button:hover {
  background-color: #3fa4e0;
}

.divAnuncios {
  margin-top: 40px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 25px;
  width: 100%;
  max-width: 1200px;
}

.sub-title {
  text-align: center;
  font-size: 100px;
  padding-bottom: 70px;
}

.sub-title span {
  color: #53bafff7;
}

.btn-box {
  display: inline-block;
  padding: 12px 28px;
  background-color: #53bafff7;
  border-radius: 40px;
  font-size: 16px;
  color: #000;
  letter-spacing: 1px;
  text-decoration: none;
  font-weight: 600;
  opacity: 0;
  animation: slideTop 1s ease forwards;
  animation-delay: 2s;
  box-shadow: 0 0 5px #53bafff7, 0 0 25px #53bafff7;
}

#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td,
#customers th {
  border: 2px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even) {
  background-color: #387ca9f7;
}

#customers tr:hover {
  background-color: #1212;
}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #124b70f7;
  color: white;
}

.header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  padding: 15px 10px;
  background-color: #387ca9f7;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 100;
}

.logo {
  position: relative;
  font-size: 25px;
  color: white;
  text-decoration: none;
  font-weight: 600;
  cursor: default;
  opacity: 0;
  animation: slideRight 1s ease forwards;
}

.navbarra a {
  display: inline-block;
  font-size: 25px;
  color: white;
  text-decoration: none;
  font-weight: 500;
  margin-left: 35px;
  transition: slideTop 0.5s ease forwards;
  opacity: 0;
  animation: slideLeft 1s ease forwards;
  animation-delay: calc(0.2s * var(--i));
}

.navbarra a:houver {
  color: #387ca9f7;
}
</style>
