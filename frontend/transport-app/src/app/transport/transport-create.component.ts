import { Endereco } from './endereco';
import { TransportService } from './transport.service';
import { Component, OnInit } from "@angular/core";
import { Transport } from "./transport"

@Component({
    templateUrl: "./transport-create.component.html",
    styles: [`.invalido{
        border-color: red;
    }`]
})
export class TransportCreateComponent implements OnInit {
    constructor(private service: TransportService) { }
    fileToUpload: File = null;
    imgHasChanged: boolean = false;
    transport: Transport = new Transport();
    endereco: Endereco;
    cepInvalido: boolean;

    ngOnInit(): void {
        this.transport.modais = [];
    }




    estados: String[] = ["AC", "AL", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
        "PR", "PE", "PI", "RJ", "RN", "RS", "RS", "RO", "RR", "SC", "SP", "SE", "TO"];





    create(): void {
        this.service.create(this.transport).subscribe({
            next: t => {
                if (this.imgHasChanged) {
                    this.service.saveLogo(t.id, this.fileToUpload).subscribe({
                        next: e => {
                            console.log("PUT logo success");
                        }
                        , error: err => {
                            console.log(err);
                        }
                    })
                }
                console.log('POST api/v1/transports/' + t.id + ' sucessful!');

            },
            error: err => {
                console.log(err);
            }
        });
    }
    semModais(): boolean {
        return this.transport.modais.length === 0;
    }
    isCepInvalido(): boolean {
        return this.cepInvalido;
    }

    getAdressByCep(): void {
        if (this.transport.cep.length === 8) {
            this.transport.bairro = "";
            this.transport.logradouro = "";
            this.transport.cidade = "";
            this.transport.uf = "";
            this.service.getAdressByCep(this.transport.cep).subscribe({
                next: e => {
                    if (e.cep === null) {
                        this.cepInvalido = true;
                    }
                    else {
                        this.transport.bairro = e.bairro;
                        this.transport.cidade = e.localidade;
                        this.transport.logradouro = e.logradouro;
                        this.transport.uf = e.uf;
                        this.cepInvalido = false;
                        console.log(e, "success");
                    }

                },
                error: err => {
                    console.log(err, "não encontrado!");

                    this.cepInvalido = true;
                }
            });
        }

    }
    s(): void {
        console.log(this.transport)
    }
    verify(modal: string): boolean {
        return this.transport.modais.indexOf(modal) > -1
    }
    change(modal: string): void {
        if (this.transport.modais.indexOf(modal) > -1) {
            this.transport.modais = this.transport.modais.filter(m => m != modal);
            console.log(this.transport.modais)
        }
        else {
            this.transport.modais.push(modal);
            console.log(this.transport.modais)
        }
    }

    setFile(files: FileList): void {
        this.fileToUpload = files.item(0);
        this.imgHasChanged = true;


    }





}
