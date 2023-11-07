import { Injectable } from '@angular/core';
import { News } from '../../models/news.interface';
import { ResultNews } from '../../models/resultNew.interface';
import { Favorite } from '../../models/favorite.interface';
import { HttpClient, HttpHandler, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http:HttpClient) { }

  getNewsFromSpaceflight(urlNews:string):Observable<ResultNews>{
    console.log(urlNews);    
    return (urlNews !== '#') ? this.http.get<ResultNews>(urlNews) : new Observable<ResultNews>();    
  }

  getAllFavoritesFromSpring(urlFavorites:string):Observable<Array<Favorite>>{
    console.log(urlFavorites);
    return (urlFavorites !== '#') ? this.http.get<Array<Favorite>>(urlFavorites) : new Observable<Array<Favorite>>();
  }

  postFavoriteFromSpring(url: any, news: News): Observable<HttpResponse<any>> {
    console.log(url);
    return (url !== '#') ? this.http.post(url, news, { observe: 'response' })
      .pipe(
        map((response: HttpResponse<any>) => {
          //const headers = response.headers;
          //const status = response.status;
          //console.log('Encabezados de la respuesta:', headers);
          //console.log('Estado HTTP:', status); 
          console.log(response); 
          return response;
        })
      )
    : new Observable<HttpResponse<any>>();
  }

  daleteFavoriteFromSpring(urlFavorites:string):Observable<any>{
    console.log(urlFavorites);
    return (urlFavorites !== '#')? this.http.delete(urlFavorites) : new Observable<any>();    
  }

}