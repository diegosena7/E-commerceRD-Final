import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarouselItemsComponent } from './carousel-items.component';

describe('CarouselItemsComponent', () => {
  let component: CarouselItemsComponent;
  let fixture: ComponentFixture<CarouselItemsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarouselItemsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarouselItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
