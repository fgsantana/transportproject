import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { Transport } from './transport';
@Injectable({
    providedIn: "root"
})
export class TransportService {
    constructor(private httpClient: HttpClient) { }
    url: string = "http://localhost:8080/api/v1/transports";

    transports: Transport[] = [
        {
            id: 1,
            email: "empresa@gmail.com",
            nome: "Empresa",
            empresa: "Transportadora",
            telefone: "8134537602",
            celular: "",
            whatsapp: "",
            modais: ['Aeroviario', 'Ferroviario', 'Dutoviario'],
            cep: "12345678",
            uf: "SP",
            cidade: "São Paulo",
            bairro: "Morumbi",
            logradouro: "Rua Teste",
            numero: 453,
            logoUrl: "http://localhost:8080/api/v1/transports/105/logo"
        },
        {
            id: 2,
            email: "empresa@gmail.com",
            nome: "Empresa",
            empresa: "Transportadora",
            telefone: "8134537602",
            celular: "",
            whatsapp: "",
            modais: ['Aeroviario', 'Ferroviario', 'Dutoviario'],
            cep: "12345678",
            uf: "SP",
            cidade: "São Paulo",
            bairro: "Morumbi",
            logradouro: "Rua Teste",
            numero: 453,
            logoUrl: "http://localhost:8080/api/v1/transports/80/logo"
        }];

    update(transport: Transport): Observable<Transport> {
        return this.httpClient.put<Transport>('http://localhost:8080/api/v1/transports/80', transport);
    }

    retrieveById(idR: number): Observable<Transport> {
        return this.httpClient.get<Transport>("http://localhost:8080/api/v1/transports/80")
    }

    saveLogo(fileToUpload: File): Observable<any> {
        const formData: FormData = new FormData();
        formData.append('logoImg', fileToUpload);
        return this.httpClient.put<any>("http://localhost:8080/api/v1/transports/80/logo", formData)
    }





}