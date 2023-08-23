import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-side-nav-user',
  templateUrl: './side-nav-user.component.html',
  styleUrls: ['./side-nav-user.component.scss']
})
export class SideNavUserComponent {

  @Output() sidenavClose = new EventEmitter();

  public onSidenavClose = () => {
    this.sidenavClose.emit();
  }

}
