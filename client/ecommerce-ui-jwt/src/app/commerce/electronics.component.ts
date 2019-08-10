import {Component} from '@angular/core';
import {AppService} from './../app.service';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {ProductsApi} from '../generated/productsapi/api/ProductsApi';
import {Product} from '../generated/productsapi/model/Product';

@Component({
    selector: 'app-electronics',
    providers: [ProductsApi],
    template: `<div class="content-container">
        <div class="content-area">
            <p>
                Content Area
            </p>
            <p>
                Electronics content goes here.
            </p>
        </div>
    </div>`
})
export class ElectronicsComponent {

    products: Product[] = [];
    product: Product;
    // private productsUrl = 'http://localhost:8082/gateway-oauth-resource/productservice/products';

    constructor(private _productsApi:ProductsApi, private _service:AppService, private _http: Http) {
        this.getProducts()
            .subscribe(
                data => {this.products = data; console.log("data from gateway server for users: "+this.products[0].product_name);},
                error =>  {console.log('users Error '+error); });
    }

    ngInit(){

    }

    getProducts(){
       return this._productsApi.findAll();

         // = {"id": 2, "category": "", "name": "iphone", "description": "apple iphone", "product_name": "iphone", "price":"700.50"};
       // return data;

        // console.log("getting users data ");
        //
        // return this._http.get(this.productsUrl)
        //     .map((res:Response) => res.json())
        //     .catch((error:any) => this.handleError(error));

        // this._service.getResource(this.productsUrl)
        //     .subscribe(
        //         data => { console.log("data from gateway server for users: "+data[0].name);},
        //         error =>  {console.log('users Error'); });
    }

    private handleError(error: any) {
        let errMsg = (error.message) ? error.message : error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.log(errMsg);
        return Observable.throw(error);
    }
}
