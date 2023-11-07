import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api/api.service';
import { Favorite } from '../models/favorite.interface';
import { catchError } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ViewModalComponent } from '../view-modal/view-modal.component';
import { API_CONFIG } from 'src/config';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {

  responseFavorites:Array<Favorite> = []; 
  urlFavorites:string = API_CONFIG.urlFavorites;

  constructor(private api:ApiService, private modalService: NgbModal){}

  ngOnInit(): void {
    this.getFavorites();
  }

  getFavorites() {
    this.api.getAllFavoritesFromSpring(this.urlFavorites).subscribe((data: Favorite[]) => {
        this.responseFavorites = data;
        console.log(this.responseFavorites);
      }
    );
  }

  // Función para eliminar un favorito
  deleteFavorite(fila: Favorite, event: Event) {
    event.preventDefault();
    console.log('Eliminar fila:', fila);  
    let url = this.urlFavorites +fila.articleId

    this.api.daleteFavoriteFromSpring(url).pipe(
      catchError((error) => {       
        return throwError(error);
      })
    ).subscribe(
      () => {
        console.log('Favorito eliminado con éxito');
        this.getFavorites();
      }
    );

  }

  // Función para ver al favorito
  viewFavorite(fila: Favorite, event: Event) {
    event.preventDefault();
    console.log('ver fila:', fila);      
    const modalRef = this.modalService.open(ViewModalComponent);
    modalRef.componentInstance.favoriteData = fila.dataArticle;
  }

}

function throwError(error: any): any {
  console.error('Error al ejecutar servicio', error);
}

