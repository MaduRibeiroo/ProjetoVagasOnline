<template>
  <header class="header">
    <a href="#" class="logo"
      ><router-link style="color: white" to="/">VAGAS</router-link></a
    >

    <nav class="navbarra">
      <a style="--i: 2" @click="irparabusca">Buscar<router-view></router-view></a>
    </nav>
  </header>
  <h1 class="sub-title" style="margin-top: 100px"><span>Sobre as vagas</span></h1>
  <div
    class="hello"
    style="width: 80%; margin-left: auto; margin-right: auto; margin-bottom: 200px"
  >
    <div>
      <form @submit.prevent="this.gravar()">
        <label for="registro">Registro</label>
        <input
          type="text"
          id="registro"
          v-model="registro"
          placeholder="Registro da vaga..."
          :disabled="modoEdicao"
        />

        <a>Empresa</a>
        <label for="empresaSelect">Selecione a empresa</label>
        <select
          id="empresaSelect"
          v-model="nome_fantasia"
          @change="buscarEmpresa()"
          :disabled="modoEdicao"
        >
          <option value="">-- Escolha a empresa --</option>
          <option v-for="emp in empresas" :key="emp.id" :value="emp.nome_fantasia">
            {{ emp.nome_fantasia }}
          </option>
        </select>

        <label for="razao_social">Razão social</label>
        <input
          type="text"
          id="razao_social"
          v-model="razao_social"
          placeholder="Razão social..."
          disabled
        />

        <label for="tipo">Tipo</label>
        <input type="text" id="tipo" v-model="tipo" placeholder="Tipo..." disabled />

        <a>Sobre a vaga</a>
        <label for="cargo">Cargo</label>
        <input
          type="text"
          id="cargo"
          v-model="cargo"
          placeholder="Digite qual seria o cargo..."
        />
        <label for="cidade">Cidade</label>
        <input
          type="text"
          id="cidade"
          v-model="cidade"
          placeholder="Digite qual a cidade.."
        />
        <label for="estado">Estado</label>
        <input
          type="text"
          id="estado"
          v-model="estado"
          placeholder="Digite qual o estado..."
        />
        <label for="pre_requisitos">Pré-requisitos</label>
        <input
          type="text"
          id="pre_requisitos"
          v-model="pre_requisitos"
          placeholder="Digite quais os pré-requisitos..."
        />
        <label for="formacao">Formação</label>
        <input
          type="text"
          id="formacao"
          v-model="formacao"
          placeholder="Digite a formação necessária..."
        />
        <label for="conhecimentos_requeridos">Conhecimentos</label>
        <input
          type="text"
          id="conhecimentos_requeridos"
          v-model="conhecimentos_requeridos"
          placeholder="Digite quais os conheciemntos necessários..."
        />
        <label for="regime">Regime</label>
        <input
          type="text"
          id="regime"
          v-model="regime"
          placeholder="Digite qual o regime..."
        />
        <label for="jornada_trabalho">Jornada de trabalho</label>
        <input
          type="text"
          id="jornada_trabalho"
          v-model="jornada_trabalho"
          placeholder="Digite qual a jornada de trabalho..."
        />
        <label for="remuneracao">Remuneração</label>
        <input
          type="text"
          id="remuneracao"
          v-model="remuneracao"
          placeholder="Digite qual seria a remuneração..."
        />

        <input type="submit" value="Confirmar" />
      </form>
    </div>
    <div style="display: flex; justify-content: flex-end">
      <button class="btn-box2" style="color: white" @click="this.mostrarForm(true)">
        Nova Vaga
      </button>
    </div>
    <div>
      <table id="customers">
        <thead>
          <tr>
            <th>Registro</th>
            <th @click="ordenarNome()">Nome</th>
            <th>Razão social</th>
            <th>Tipo</th>
            <th>Cargo</th>
            <th>Cidade</th>
            <th>Estado</th>
            <th>Pré-requisitos</th>
            <th>Formação</th>
            <th>Conhecimentos</th>
            <th>Regime</th>
            <th>Jornada de trabalho</th>
            <th>Remuneração</th>
            <th colspan="2">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="vag in this.vagas">
            <td>{{ vag.registro }}</td>
            <td>{{ vag.empresa.nome_fantasia }}</td>
            <td>{{ vag.empresa.razao_social }}</td>
            <td>{{ vag.empresa.tipo }}</td>
            <td>{{ vag.cargo }}</td>
            <td>{{ vag.cidade }}</td>
            <td>{{ vag.estado }}</td>
            <td>{{ vag.pre_requisitos }}</td>
            <td>{{ vag.formacao }}</td>
            <td>{{ vag.conhecimentos_requeridos }}</td>
            <td>{{ vag.regime }}</td>
            <td>{{ vag.jornada_trabalho }}</td>
            <td>{{ vag.remuneracao }}</td>
            <td><button @click="this.alterar(vag.registro)">Alterar</button></td>
            <td><button @click="this.apagar(vag.registro)">Apagar</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "FormVagas",
  props: {
    msg: String,
  },
  mounted() {
    this.carregarEmpresas();
    this.carregarDados();
  },
  data() {
    return {
      modoEdicao: false,
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
      empresas: [],
    };
  },
  methods: {
    irparabusca() {
      this.$router.push({
        name: "buscarVagas",
        params: { msg: "algumaMensagem" },
        query: { nivel: this.nivel },
      });
    },
    gravar() {
      const urlBase = "http://localhost:8080/apis/vagas";

      const data = {
        registro: this.registro,
        empresa: {
          nome_fantasia: this.nome_fantasia,
          razao_social: this.razao_social,
          tipo: this.tipo,
        },
        cargo: this.cargo,
        cidade: this.cidade,
        estado: this.estado,
        pre_requisitos: this.pre_requisitos,
        formacao: this.formacao,
        conhecimentos_requeridos: this.conhecimentos_requeridos,
        regime: this.regime,
        jornada_trabalho: this.jornada_trabalho,
        remuneracao: this.remuneracao,
      };

      if (this.modoEdicao) {
        // ⚠️ ALTERAR (PUT)
        axios
          .put(urlBase, data)
          .then(() => {
            alert("Vaga alterada com sucesso!");
            this.carregarDados();
            this.mostrarForm(false);
          })
          .catch((error) => {
            console.error(error);
            alert("Erro ao alterar vaga.");
          });
      } else {
        // ➕ CADASTRAR (POST)
        axios
          .post(urlBase, data)
          .then(() => {
            alert("Vaga cadastrada com sucesso!");
            this.carregarDados();
            this.mostrarForm(false);
          })
          .catch((error) => {
            console.error(error);
            alert("Erro ao cadastrar vaga.");
          });
      }
    },

    apagar(registro) {
      axios
        .delete("http://localhost:8080/apis/vagas/" + registro)
        .then(() => {
          this.carregarDados();
          alert("Vaga apagada!");
        })
        .catch((error) => {
          alert("Erro ao apagar: " + " vaga com interesse");
        });
    },
    alterar(registro) {
      this.modoEdicao = true;
      this.formOn = true;

      // 1. Busca a vaga na lista já carregada
      const vaga = this.vagas.find((v) => v.registro === registro);

      if (!vaga) {
        alert("Vaga não encontrada!");
        return;
      }

      // 2. Preenche o formulário com os dados da vaga
      this.registro = vaga.registro;

      this.nome_fantasia = vaga.empresa.nome_fantasia;
      this.razao_social = vaga.empresa.razao_social;
      this.tipo = vaga.empresa.tipo;

      this.cargo = vaga.cargo;
      this.cidade = vaga.cidade;
      this.estado = vaga.estado;
      this.pre_requisitos = vaga.pre_requisitos;
      this.formacao = vaga.formacao;
      this.conhecimentos_requeridos = vaga.conhecimentos_requeridos;
      this.regime = vaga.regime;
      this.jornada_trabalho = vaga.jornada_trabalho;
      this.remuneracao = vaga.remuneracao;
    },
    mostrarForm(flag) {
      this.formOn = flag;
      if (flag) {
        this.modoEdicao = false;
        this.limparCampos();
      }
    },
    limparCampos() {
      this.registro = "";
      this.nome_fantasia = "";
      this.razao_social = "";
      this.tipo = "";
      this.cargo = "";
      this.cidade = "";
      this.estado = "";
      this.pre_requisitos = "";
      this.formacao = "";
      this.conhecimentos_requeridos = "";
      this.regime = "";
      this.jornada_trabalho = "";
      this.remuneracao = "";
    },

    buscarEmpresa() {
      const empresa = this.empresas.find((e) => e.nome_fantasia === this.nome_fantasia);

      if (empresa) {
        this.razao_social = empresa.razao_social;
        this.tipo = empresa.tipo;
      } else {
        this.razao_social = "";
        this.tipo = "";
      }
    },

    carregarEmpresas() {
      axios
        .get("http://localhost:8080/apis/empresa/get-all")
        .then((result) => {
          this.empresas = result.data;
        })
        .catch(() => {
          alert("Erro ao carregar empresas!");
        });
    },
    carregarDados() {
      axios
        .get("http://localhost:8080/apis/vagas/get-all")
        .then((result) => {
          this.vagas = result.data;
        })
        .catch((error) => {
          alert(error);
        });
    },
    ordenarNome() {
      this.vagas.sort((a, b) =>
        a.empresa.nome_fantasia.localeCompare(b.empresa.nome_fantasia)
      );
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

.sub-title {
  text-align: center;
  font-size: 100px;
  padding-bottom: 70px;
}

.sub-title span {
  color: #53bafff7;
}

.btn-box2 {
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
  animation-delay: 0.5s;
  box-shadow: 0 0 5px #53bafff7, 0 0 25px #53bafff7;
}

#customers {

  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td,
#customers th {
  font-size: 10px;
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
