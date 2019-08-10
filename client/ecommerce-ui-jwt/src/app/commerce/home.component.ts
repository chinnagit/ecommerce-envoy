import {Component} from '@angular/core';
import {LoginService} from '../login/login.service'
import {Router} from "@angular/router";
 
@Component({
    selector: 'home-header',
    providers: [LoginService],
  template: `<div class="main-container" >
      <header class="header header-6">
          <div class="branding" style="width: 78%; float:left">
              <a href="#">
                  <!--<clr-icon shape="vm-bug"></clr-icon>-->
                  <span class="title">Welcome to My Ecommerce Store</span>
              </a>
          </div>
          <div class="header-nav" style="width: 8%; float:right">
              <a class="btn btn-default pull-right"(click)="logout()" href="login">Logout</a>
          </div>
          <div class="header-nav" style="width: 8%; float:right">
              <a class="btn btn-default pull-right" [routerLink]=" ['/main/cart']">Cart</a>
          </div>
          <div class="header-nav" style="width: 8%; float:right">
              <a class="btn btn-default pull-right" [routerLink]=" ['/main/user']">User</a>
          </div>
      </header>
      <router-outlet></router-outlet>

      <!--<main-details></main-details>-->
</div>`
})
//<!--<foo-details></foo-details>-->
export class HomeComponent {
 
    constructor(private _router: Router, private _service:LoginService){
        console.log("Landed in home component");
    }
 
    ngOnInit(){
        this._service.checkCredentials();
        this._router.navigate(['/main']);

    }
 
    logout() {
        this._service.logout();
    }
}