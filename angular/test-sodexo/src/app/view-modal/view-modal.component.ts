import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-view-modal',
  templateUrl: './view-modal.component.html',
  styleUrls: ['./view-modal.component.css']
})
export class ViewModalComponent implements OnInit {
  @Input() favoriteData: any;
  dataArticleObject:any;
  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {    
    try {
      this.dataArticleObject = JSON.parse(this.favoriteData);
    } catch (error) {
      //console.error('ya es JSON:', error);
      this.dataArticleObject = this.favoriteData;
    }
    console.log("ViewModalComponent:");
    console.log(this.dataArticleObject);    
  }
}




