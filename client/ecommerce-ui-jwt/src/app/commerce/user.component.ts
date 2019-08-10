import {Component} from '@angular/core';
import {User} from '../generated/productsapi/model/User';
import {UserApi} from '../generated/productsapi/api/UserApi';

@Component({
    selector: 'app-user',
    providers: [UserApi],
    templateUrl: 'user.component.html'
})
export class UserComponent {
    users: User[] = [];
    constructor(private _usersApi:UserApi){
        this.getUsers()
            .subscribe(
                data => {this.users = data; console.log("[UserComponent] data from gateway server for users: "+this.users[0].name)},
                error =>  {console.log('[UserComponent] get user Error '+error); });
    }

    ngInit(){

    }

    getUsers() {
        return this._usersApi.findAll();
    }
}
