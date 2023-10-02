import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapImageSimpleListComponent } from './map-image-simple-list.component';

describe('MapImageSimpleListComponent', () => {
  let component: MapImageSimpleListComponent;
  let fixture: ComponentFixture<MapImageSimpleListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MapImageSimpleListComponent]
    });
    fixture = TestBed.createComponent(MapImageSimpleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
