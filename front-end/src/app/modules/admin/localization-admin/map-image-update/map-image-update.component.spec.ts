import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapImageUpdateComponent } from './map-image-update.component';

describe('MapImageUpdateComponent', () => {
  let component: MapImageUpdateComponent;
  let fixture: ComponentFixture<MapImageUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MapImageUpdateComponent]
    });
    fixture = TestBed.createComponent(MapImageUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
