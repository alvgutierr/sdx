import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api/api.service';
import { ResultNews } from '../models/resultNew.interface';
import { News } from '../models/news.interface';
import { HttpResponse } from '@angular/common/http';
import { ViewModalComponent } from '../view-modal/view-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { API_CONFIG } from 'src/config';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {
  [x: string]: any;

  responseNews!: ResultNews;
  urlNews:string = API_CONFIG.urlNews;
  nextUrl:string = "#";
  previousUrl:string = "#";
  urlFavorites:string = API_CONFIG.urlFavorites;
  
  constructor(private api:ApiService, private modalService: NgbModal,private router: Router){}

  ngOnInit(): void {
    this.getNewsToday(this.urlNews);
  }

  getNewsToday(url:string){    
    this.api.getNewsFromSpaceflight(url).subscribe((data: ResultNews) => {
      this.responseNews = data;
      this.nextUrl = (data.next) ? data.next : this.nextUrl;
      this.previousUrl = (data.previous) ? data.previous : this.previousUrl;
    });
  }

  // Función para editar la fila
  addFavorite(news: News, event: Event) {
    event.preventDefault();
    console.log('addFavorite fila:', news);
    this.api.postFavoriteFromSpring(this.urlFavorites, news).subscribe(
      (response) => {        
        if (response instanceof HttpResponse) {
          console.log("Favorito agregado:");
          console.log(response);
          const status = response.status;
          console.log('---- Estado HTTP:', status);

          if(status == 200){
            this.router.navigate(['/favorites']); 
          }          
        }        
      }
    );
  }

  // Función para eliminar la fila
  viewNews(fila: News, event: Event) {
    event.preventDefault();
    console.log('viewNews fila:', fila);    
    const modalRef = this.modalService.open(ViewModalComponent);
    modalRef.componentInstance.favoriteData = fila;
  }

  sliceSummary(summary:string){
    return summary.slice(0,75);
  }
}
