import { TransportService } from "../transport/transport.service"
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: "confirm-delete",
    templateUrl: "./delete-confirm.component.html",
    styles: [`.invalido{
        border-color: red;
    }`]
})
export class DeleteConfirmComponent implements OnInit {
    constructor(private activatedRoute: ActivatedRoute, private service: TransportService) { }
    transportId: number;
    ngOnInit(): void {
        this.transportId = +this.activatedRoute.snapshot.paramMap.get('id');
    }

    deleteTransport(): void {
        this.service.deleteTransport(this.transportId).subscribe({
            next: t => {
                console.log(this.transportId + " deleted!")
            },
            error: err => {
                console.log(err);
            }
        });

    }









}