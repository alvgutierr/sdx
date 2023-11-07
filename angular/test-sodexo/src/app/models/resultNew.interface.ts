import { News } from "./news.interface";

export interface ResultNews{
    
    count:number;
    next:string;
    previous:string;
    results:Array<News>;
}