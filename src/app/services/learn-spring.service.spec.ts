import { TestBed } from '@angular/core/testing';

import { LearnSpringService } from './learn-spring.service';

describe('LearnSpringService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LearnSpringService = TestBed.get(LearnSpringService);
    expect(service).toBeTruthy();
  });
});
