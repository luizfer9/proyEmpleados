import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers: [DatePipe]
})
export class NavbarComponent implements OnInit {
  myDate = new Date();
  fecha: string;
  constructor(private datePipe: DatePipe) {
    this.cargarFechaDia();
   }

  ngOnInit(): void {
  }

  cargarFechaDia() {
    this.fecha = this.datePipe.transform(this.myDate, 'dd-MM-yyyy');
  }

}
