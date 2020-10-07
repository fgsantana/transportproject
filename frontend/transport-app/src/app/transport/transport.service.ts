import { Endereco } from './endereco';
import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { Transport } from './transport';
@Injectable({
    providedIn: "root"
})
export class TransportService {
    constructor(private httpClient: HttpClient) { }
    url: string = "http://localhost:8080/api/v1/transports/";



    create(transport: Transport): Observable<Transport> {
        return this.httpClient.post<Transport>(this.url, transport);
    }

    update(transport: Transport): Observable<Transport> {
        return this.httpClient.put<Transport>(this.url + transport.id, transport);
    }

    retrieveById(idR: number): Observable<Transport> {
        return this.httpClient.get<Transport>(this.url + idR)
    }

    saveLogo(id: number, fileToUpload: File): Observable<any> {
        const formData: FormData = new FormData();
        formData.append('logoImg', fileToUpload);
        return this.httpClient.put<any>(this.url + id + "/logo", formData)
    }


    getAdressByCep(cep: string): Observable<Endereco> {
        return this.httpClient.get<Endereco>(this.url + "adress/" + cep);
    }

    deleteTransport(id: number): Observable<any> {
        return this.httpClient.delete<any>(this.url + id);
    }

    retrieveAll(): Observable<Transport[]> {
        return this.httpClient.get<Transport[]>(this.url);
    }





}