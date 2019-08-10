import {Component} from '@angular/core';
import {ProductsApi} from '../generated/productsapi/api/ProductsApi';
import {Product} from '../generated/productsapi/model/Product';
import {CartService} from "./cart.service";

@Component({
    selector: 'app-catalog',
    providers: [ProductsApi],
    templateUrl: 'catalog.component.html'
})
export class CatalogComponent {
    products: Product[] = [];
    constructor(private _productsApi:ProductsApi, private cartService: CartService){
        this.getProducts()
            .subscribe(
                data => {this.products = data; console.log("[ProductComponent] data from gateway server for products: "+this.products[0].product_name)},
                error =>  {console.log('[ProductComponent] product Error '+error); });
    }
    ngInit(){

    }

    addToCart(index){
        this.cartService.save(this.products[index]);
    }

    getProducts() {
        return this._productsApi.findAll();
    }
}
