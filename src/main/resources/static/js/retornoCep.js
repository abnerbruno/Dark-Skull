class Validator {

  constructor() {
    this.validations = [
      'data-min-length',
      'data-max-length',
      'data-only-letters',
      'data-email-validate',
      'data-required',
      'data-equal',
      'data-password-validate',
      'isValidCPF',
      'calcIdade',
      'onlyletters',
      'onlynumbers',
      'onlynumbersCEP',
    ]
  }

  // inicia a validação de todos os campos
  validate(form) {
    // limpa todas as validações antigas
    let currentValidations = document.querySelectorAll('form .error-validation');
    if(currentValidations.length) {
      this.cleanValidations(currentValidations);
    }

    // pegar todos inputs
    let inputs = form.getElementsByTagName('input');
    // transformar HTMLCollection em arr
    let inputsArray = [...inputs];

    // loop nos inputs e validação mediante aos atributos encontrados
    inputsArray.forEach(function(input, obj) {
      // fazer validação de acordo com o atributo do input
      for(let i = 0; this.validations.length > i; i++) {
        if(input.getAttribute(this.validations[i]) != null) {
          // limpa string para saber o método
          let method = this.validations[i].replace("data-", "").replace("-", "");
          // valor do input
          let value = input.getAttribute(this.validations[i])
          // invoca o método
          this[method](input,value);
        }
      }
    }, this);

  }

  // método para validar se tem um mínimo de caracteres
  minlength(input, minValue) {
    let inputLength = input.value.length;
    let errorMessage = `O campo precisa ter pelo menos ${minValue} caracteres`;
    if(inputLength < minValue) {
      this.printMessage(input, errorMessage);
    }
  }

  // método para validar se passou do máximo de caracteres
  maxlength(input, maxValue) {
    let inputLength = input.value.length;
    let errorMessage = `O campo precisa ter menos que ${maxValue} caracteres`;
    if(inputLength > maxValue) {
      this.printMessage(input, errorMessage);
    }
  }

  // método para validar strings que só contem letras
  onlyletters(input) {
    let re = /^[A-Za-z]+$/;;
    let inputValue = input.value;
    let errorMessage = "Este campo não aceita números nem caracteres especiais";
    if(!re.test(inputValue)) {
      this.printMessage(input, errorMessage);
    }
  }

  onlynumbers(input) {
    let re = /^[0-9]+$/;;
    let inputValue = input.value;
    let errorMessage = "Este campo não aceita letras nem caracteres especiais";
    if(!re.test(inputValue)) {
      this.printMessage(input, errorMessage);
    }
  }

  onlynumbersCEP(input){
    let re = /^[0-9]+$+\-/;;
    let inputValue = input.value;
    let errorMessage = "Este campo não aceita letras";
    if(!re.test(inputValue)) {
      this.printMessage(input, errorMessage);
    }
  }

  // método para validar e-mail
  emailvalidate(input) {
    let re = /\S+@\S+\.\S+/;
    let email = input.value;
    let errorMessage = "Insira um e-mail no padrão bruno@email.com";
    if(!re.test(email)) {
      this.printMessage(input, errorMessage);
    }
  }

  // Valida CPF
  isValidCPF(input) {

    let cpf = input.value
    let errorMessage = "Informe um CPF valido"
    // Validar se é String
    if (typeof cpf !== 'string') this.printMessage(input, errorMessage)
    // Tirar formatação
    cpf = cpf.replace(/[^\d]+/g, '')
    // Validar se tem tamanho 11 ou se é uma sequência de digitos repetidos
    if (cpf.length !== 11 || !Array.from(cpf).filter(e => e !== cpf[0]).length) {
      this.printMessage(input, errorMessage);
    }
    // String para Array
    cpf = cpf.split('')
    const validator = cpf
        // Pegar os últimos 2 digitos de validação
        .filter((digit, index, array) => index >= array.length - 2 && digit)
        // Transformar digitos em números
        .map( el => +el )
    const toValidate = pop => cpf
        // Pegar Array de items para validar
        .filter((digit, index, array) => index < array.length - pop && digit)
        // Transformar digitos em números
        .map(el => +el)
    const rest = (count, pop) => (toValidate(pop)
        // Calcular Soma dos digitos e multiplicar por 10
        .reduce((soma, el, i) => soma + el * (count - i), 0) * 10)
        // Pegar o resto por 11
        % 11
        // transformar de 10 para 0
        % 10
    if( !(!(rest(10,2) !== validator[0] || rest(11,1) !== validator[1]))) {
      this.printMessage(input, errorMessage);
    }
  }

  // Validar idade
  calcIdade(input) {
    let data = new Date(input.value)
    let idadeMin = 18
    let idadeMax = 112
    let errorMessageMim = "Apenas maiores de 18 anos podem fazer o registro"
    let errorMessageMax = "Apenas menores de 112 anos podem fazer o registro"
    var d = new Date
    let ano_atual = d.getFullYear()
    let mes_atual = d.getMonth() + 1
    let dia_atual = d.getDate()
    let ano_aniversario = data.getFullYear()
    let mes_aniversario = data.getMonth() + 1
    let dia_aniversario = data.getDate()
    let quantos_anos = ano_atual - ano_aniversario
    if (mes_atual < mes_aniversario || mes_atual == mes_aniversario && dia_atual < dia_aniversario) {
        quantos_anos--;
    }
    if ((quantos_anos < idadeMin)){
      this.printMessage(input, errorMessageMim);
    }
    if ((quantos_anos >= idadeMax)){
      this.printMessage(input, errorMessageMax);
    }
  }


  // verificar se um campo está igual o outro
  equal(input, inputName) {
    let inputToCompare = document.getElementsByName(inputName)[0];
    let errorMessage = `Este campo precisa estar igual ao ${inputName}`;
    if(input.value != inputToCompare.value) {
      this.printMessage(input, errorMessage);
    }
  }

  // método para exibir inputs que são necessários
  required(input) {
    let inputValue = input.value;
    if(inputValue === '') {
      let errorMessage = "Este campo é obrigatório";
      this.printMessage(input, errorMessage);
    }
  }

  // validando o campo de senha
  passwordvalidate(input) {
    // explodir string em array
    let charArr = input.value.split("");
    let uppercases = 0;
    let numbers = 0;
    for(let i = 0; charArr.length > i; i++) {
      if(charArr[i] === charArr[i].toUpperCase() && isNaN(parseInt(charArr[i]))) {
        uppercases++;
      } else if(!isNaN(parseInt(charArr[i]))) {
        numbers++;
      }
    }
    if(uppercases === 0 || numbers === 0) {
      let errorMessage = "A senha precisa um caractere maiúsculo e um número";
      this.printMessage(input, errorMessage);
    }
  }

  // método para imprimir mensagens de erro
  printMessage(input, msg) {
    // checa os erros presentes no input
    let errorsQty = input.parentNode.querySelector('.error-validation');
    // imprimir erro só se não tiver erros
    if(errorsQty === null) {
      let template = document.querySelector('.error-validation').cloneNode(true);
      template.textContent = msg;
      let inputParent = input.parentNode;
      template.classList.remove('template');
      inputParent.appendChild(template);
    }
  }

  // remove todas as validações para fazer a checagem novamente
  cleanValidations(validations) {
    validations.forEach(el => el.remove());
  }


}

let form = document.getElementById('register-form');
let submit = document.getElementById('btn-submit');

let validator = new Validator();

const cep = document.querySelector('#cep')
cep.addEventListener("blur",(e)=>{
  let busca = cep.value.replace("-","")
  const options = {
    method: 'GET',
    mode: 'cors',
    cache: 'default'
  }
  fetch(`https://viacep.com.br/ws/${busca}/json/`, options)
  .then(response =>{ response.json()
    .then(data => showData(data))
  })
  .catch(e => document.querySelector('#cep').value = 'Erro ao localizar o CEP')
})

const showData = (result)=> {
  for (const campo in result){
    if(document.querySelector("#"+campo)){
      document.querySelector("#"+campo).value = result[campo]
    }
  }
}

