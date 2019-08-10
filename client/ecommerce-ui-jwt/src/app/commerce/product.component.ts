import {Component} from '@angular/core';

@Component({
})
export class ProductComponent {
    name: string;
    price: number ;
    category: string ;
    constructor( product_name: string, category: string, price: number){
        this.name = product_name;
        this.category = category;
        this.price = price;
    }
}