import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pessoas-pesquisa',
  templateUrl: './pessoas-pesquisa.component.html',
  styleUrls: ['./pessoas-pesquisa.component.css']
})
export class PessoasPesquisaComponent {

  pessoas = [
    { nome: 'Maria Tereza', cidade: 'Uberaba', estado: 'MG', ativo: true},
    { nome: 'Romildo da Silva', cidade: 'Taguatinga', estado: 'DF', ativo: false},
    { nome: 'Luiz Canamari', cidade: 'Sobradinho', estado: 'DF', ativo: true},
    { nome: 'Gertudes Pinto', cidade: 'Porto Seguro', estado: 'BA', ativo: false}
  ];
}
