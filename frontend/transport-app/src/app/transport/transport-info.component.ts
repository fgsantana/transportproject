import { TransportService } from './transport.service';
import { Component, OnInit } from "@angular/core";
import { Transport } from "./transport"

@Component({
    selector: "transport-info",
    templateUrl: "./transport-info.component.html"
})
export class TransportInfoComponent implements OnInit {
    constructor(private service: TransportService) { }
    fileToUpload: File = null;
    transport: Transport;
    ngOnInit(): void {
        this.service.retrieveById(80).subscribe({
            next: t=>{
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
    
    


}
