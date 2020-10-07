import { Component } from "@angular/core";
import { Transport } from "./transport"

@Component({
    selector: "transport-info",
    templateUrl: "./transport-info.component.html"
})
export class TransportInfoComponent  {


transport:Transport =   {id: 1,
email: "empresa@gmail.com",
nome: "Empresa",
empresa: "Transportadora",
telefone:"8134537602",
celular: "",
whatsapp: "",
modais: ['Aeroviario', 'Ferroviario', 'Dutoviario'],
cep: "12345678",
uf: "SP",
cidade: "São Paulo",
bairro: "Morumbi",
logradouro: "Rua Teste",
numero: 453,
logoUrl: "http://localhost:8080/api/v1/transport/1/logo"


}
verify(modal: string) : boolean{
    return this.transport.modais.indexOf(modal) > -1
}
change(modal: string) : void{
    if(this.transport.modais.indexOf(modal) > -1){
     this.transport.modais=this.transport.modais.filter(m => m!=modal);
            console.log(this.transport.modais)
    }
    else{
        this.transport.modais.push(modal);
        console.log(this.transport.modais)
    }
}
}
