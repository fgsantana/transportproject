import { Endereco } from './endereco';
import { TransportService } from './transport.service';
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from '@angular/router';
import { Transport } from "./transport"

@Component({
    selector: "transport-info",
    templateUrl: "./transport-info.component.html",
    styles: [`.invalido{
        border-color: red;
    }`]
})
export class TransportInfoComponent implements OnInit {
    constructor(private activatedRoute: ActivatedRoute, private service: TransportService) { }
    fileToUpload: File = null;
    imgHasChanged: boolean = false;
    transport: Transport;
    endereco: Endereco;
    cepInvalido: boolean;



    ngOnInit(): void {

        this.service.retrieveById(+ this.activatedRoute.snapshot.paramMap.get('id')).subscribe({
            next: t => {
                this.transport = t;
                console.log('GET api/v1/transports/' + t.id + ' sucessful!');
            },
            error: err => {
                console.log(err);
            }
        });

    }

    estados: String[] = ["AC", "AL", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
        "PR", "PE", "PI", "RJ", "RN", "RS", "RS", "RO", "RR", "SC", "SP", "SE", "TO"];





    update(): void {
        this.service.update(this.transport).subscribe({
            next: t => {

                console.log('PUT api/v1/transports/' + 80 + ' sucessful!');
            },
            error: err => {
                console.log(err);
            }
        });
        if (this.imgHasChanged) {

            this.service.saveLogo(this.transport.id, this.fileToUpload).subscribe({
                next: e => {
                    console.log("Sucess");
                }
                , error: err => {
                    console.log(err);
                }
            })
        }
    }
    semModais(): boolean {
        return this.transport.modais.length === 0;
    }
    isCepInvalido(): boolean {
        return this.cepInvalido;
    }

    getAdressByCep(): void {
        this.transport.bairro = "";
        this.transport.logradouro = "";
        this.transport.cidade = "";
        this.transport.uf = "";
        if (this.transport.cep.length === 8) {
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
