import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BasketService} from "../../../../core/service/booking/basket.service";
import {Location} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackbarErrorComponent} from "../../../shared/snackbar-error/snackbar-error.component";
import {SnackbarMessageComponent} from "../../../shared/snackbar-message/snackbar-message.component";
import {EventEntityDTOModel} from "../../../../core/service/event/models/EventEntityDTO-model";
import {EventEntityService} from "../../../../core/service/event/event-entity.service";
import {CustomerAdminDTOModel} from "../../../../core/service/customers/models/CustomerAdminDTO-model";
import {CustomersService} from "../../../../core/service/customers/customers.service";

@Component({
  selector: 'app-basket-admin-add',
  templateUrl: './basket-admin-add.component.html',
  styleUrls: ['./basket-admin-add.component.scss']
})
export class BasketAdminAddComponent implements OnInit{

  basketItemForm!: FormGroup;

  eventEntityLightList!: Array<EventEntityDTOModel>;
  selectedEventEntity: EventEntityDTOModel;

  customerLightList!: Array<CustomerAdminDTOModel>;
  selectedCustomer: CustomerAdminDTOModel;


  constructor(
    private formBuilder: FormBuilder,
    private basketService: BasketService,
    private location: Location,
    private _snackBar: MatSnackBar,
    private eventEntityService: EventEntityService,
    private customerService: CustomersService,

  ) {
  }
  ngOnInit(): void {

    this.getEventEntityList();
    this.getCustomerList();

    this.basketItemForm = this.formBuilder.group(
      {
        name:['',{validators:[Validators.required]}],
        eventId:['', {validators:[Validators.required]}],
        deletable:[true, {validators:[Validators.required]}],
        authId:['', {validators:[Validators.required]}]

      }
    )
  }

  getSelectedEventEntity($event: EventEntityDTOModel) {
    this.selectedEventEntity = $event
    this.basketItemForm.get('eventId')?.setValue(this.selectedEventEntity.id);
    this.basketItemForm.get('name')?.setValue(this.selectedEventEntity.name);

  }

  getEventEntityList(){
    this.eventEntityService.getListEvent().subscribe(value => {
      this.eventEntityLightList = value;
    })
  }

  getSelectedCustomer($event: CustomerAdminDTOModel) {
    this.selectedCustomer = $event
    this.basketItemForm.get('authId')?.setValue(this.selectedCustomer.authID);

  }

  getCustomerList(){
    this.customerService.getCustomersAdminList().subscribe(value => {
      this.customerLightList = value;
    })
  }

  openSnackBarError(message: string) {
    this._snackBar.openFromComponent(SnackbarErrorComponent, {
      data: message,
    });
  }

  openSnackBar(message: string) {
    this._snackBar.openFromComponent(SnackbarMessageComponent, {
      data: message,
    });
  }

  saveBasketItem(){
    this.basketService.postAdminBasket(this.basketItemForm.getRawValue()).subscribe(
      {
        next: () =>{
          this.openSnackBar("Item Created Successfully!")

          this.location.back()
        },
        error: err => {
          this.openSnackBarError(err.error.text)


        }
      }
    )
  }

}
