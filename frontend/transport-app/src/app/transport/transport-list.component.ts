import { TransportService } from './transport.service';
import { Component, OnInit } from "@angular/core";
import { Transport } from "./transport"
import { TitleCasePipe } from '@angular/common';

@Component({
    templateUrl: "./transport-list.component.html",

})
export class TransportListComponent implements OnInit {
    constructor(private service: TransportService) { }
    filteredTransports: Transport[]=[];
    transports: Transport[]=[];
    filterByName: string;
    filterByUf: string;
    filterByCity: string;
    filterByModal: string;
    allModais: string[] = [];
    allCities: string[] = [];
    allUfs: string[] = []




    ngOnInit(): void {
        this.retrieveAll();



    }


    retrieveAll(): void {
        this.service.retrieveAll().subscribe({
            next: all => {
                this.transports = all;
                this.filteredTransports = all;
                
            },
            error: err => {
                console.log(err);
            }
        })


    }
    setFilterByUf(uf: string): void {
       this.filteredTransports = this.transports;
        this.filterByUf = uf;
        this.filteredTransports = this.filteredTransports.filter(t => t.uf === uf)

    }
    setFilterByCity(city: string): void {
        this.filteredTransports = this.transports;
        this.filterByCity = city;
        this.filteredTransports = this.filteredTransports.filter(t => t.cidade === city)

    }

    setFilterByModal(modal: string): void {
        this.filteredTransports = this.transports;
        
        this.filterByModal = modal;
        this.filteredTransports = this.filteredTransports.filter(t => (t.modais.indexOf(modal) > -1))

    }
    setFilterByName(): void {
         this.filteredTransports = this.transports;
        if (!(this.filterByName === "")) {
            this.filteredTransports = this.filteredTransports.filter(t => t.nome.toLocaleLowerCase().indexOf(this.filterByName.toLocaleLowerCase()) > -1);
        }
    }

    



    getAllModaisFromAll(): string[] {
        this.transports.forEach(t => {
            t.modais.forEach(m => {
                if (!(this.allModais.indexOf(m) > -1)) {

                    this.allModais.push(m);
                }
            })
        })
        return this.allModais;
    }

    getAllCities(): string[]{
        this.transports.forEach( t =>{
            if (!(this.allCities.indexOf(t.cidade) > -1)){
                this.allCities.push(t.cidade);
            }
        })
        return this.allCities;
    }
    getAllUfs(): string[]{
        this.transports.forEach( t =>{
            if (!(this.allUfs.indexOf(t.uf) > -1)){
                this.allUfs.push(t.uf);
            }
        })
        return this.allUfs;
    }











}
